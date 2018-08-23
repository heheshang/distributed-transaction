package com.distributed.transation.scheduled.impl;

import com.distributed.transaction.enums.message.AreadlyDeadEnum;
import com.distributed.transaction.enums.message.MessageStatusEnum;
import com.distributed.transaction.enums.trade.TradeStatusEnum;
import com.distributed.transaction.module.message.domain.TransactionMessageEntity;
import com.distributed.transaction.module.message.vo.TransactionMessage;
import com.distributed.transaction.module.trade.domain.TradePaymentRecordEntity;
import com.distributed.transaction.module.trade.repository.TradePaymentRecordRepository;
import com.distributed.transaction.module.trade.vo.TradePaymentRecord;
import com.distributed.transaction.utils.BeanMapping;
import com.distributed.transation.scheduled.ITransactionMessageScheduled;
import com.distributed.transation.scheduled.biz.TransactionMessageService;
import com.google.common.collect.Maps;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 超时消息处理业务处理业务实现
 *
 * @author ssk www.distributed.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-02-下午 5:03
 */
@Component("transactionMessageScheduled")
@Log4j2
public class TransactionMessageScheduledImpl implements ITransactionMessageScheduled {

    @Autowired
    private TransactionMessageService transactionMessageService;

    @Autowired
    private TradePaymentRecordRepository tradePaymentRecordRepository;

    @Autowired
    private Map<Integer, Integer> sendTime;

    /**
     *
     */
    @Override
    public void handleWaitingConfirmTimeOutMessages() {

        Map<String, Object> qureyMap = Maps.newHashMap();

        qureyMap.put("status", MessageStatusEnum.WAITING_CONFIRM);


        int pageNum = 0;

        int pageSize = 200;

        Page<TransactionMessageEntity> page = transactionMessageService.getTransactionMessage(pageNum, pageSize, qureyMap);

        log.info("开始处理[WAITING_CONFIRM]状态的消息,待发送消息数量====>[{}]", page.getTotalPages());

        List<TransactionMessage> messages = BeanMapping.mapList(page.getContent(), TransactionMessage.class);


        for (TransactionMessage message : messages) {

            if (StringUtils.isEmpty(message.getField1())) {
                continue;
            }

            TradePaymentRecordEntity recordEntity = tradePaymentRecordRepository.getByBankOrderNo(message.getField1());

            if (Objects.nonNull(recordEntity)) {

                TradePaymentRecord record = BeanMapping.map(recordEntity, TradePaymentRecord.class);

                TradeStatusEnum statusEnum = TradeStatusEnum.getEnum(record.getStatus());

                if (statusEnum != null) {

                    switch (statusEnum) {
                        case SUCCESS:
                            transactionMessageService.confirmAndSendMessage(message.getMessageId());
                            break;
                        case WAITING_PAYMENT:
                            transactionMessageService.deleteMessageByMessageId(message.getMessageId());
                        default:
                            break;
                    }
                }
            }
        }

        log.info("待发送消息数量====>[{}]", page.getTotalPages());
    }

    @Override
    public void handleSendingTimeOutMessages() {

        Map<String, Object> qureyMap = Maps.newHashMap();

        qureyMap.put("status", MessageStatusEnum.SENDING);
        qureyMap.put("areadlyDead", AreadlyDeadEnum.NO);

        int pageNum = 0;
        int pageSize = 200;


        Page<TransactionMessageEntity> page = transactionMessageService.getTransactionMessage(pageNum, pageSize, qureyMap);

        log.info("开始处理[SENDING]状态的消息,待发送消息数量====>[{}]", page.getTotalPages());


        List<TransactionMessage> messages = BeanMapping.mapList(page.getContent(), TransactionMessage.class);

        int maxTimes = 5;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (TransactionMessage message : messages) {

            try {


                log.info("开始处理[SENDING]消息ID为[" + message.getMessageId() + "]的消息");

                //超过最大次数直接退出
                log.info("[SENDING]消息ID为[" + message.getMessageId() + "]的消息,已经重新发送的次数[" + message.getMessageSendTimes() + "]");

                if (maxTimes < message.getMessageSendTimes()) {
                    transactionMessageService.setMessageToAreadlyDead(message.getMessageId());
                    continue;
                }

                int reSendTimes = message.getMessageSendTimes();
                int times = sendTime.get(reSendTimes == 0 ? 1 : reSendTimes);
                long currentTimeInMillis = Calendar.getInstance().getTimeInMillis();
                long needTime = currentTimeInMillis - times * 60 * 1000;
                long hasTime = message.getEditTime().getTime();
                // 判断是否达到了可以再次发送的时间条件
                if (hasTime > needTime) {
                    log.info("currentTime[" + sdf.format(new Date()) + "],[SENDING]消息上次发送时间[" + sdf.format(message.getEditTime()) + "],必须过了[" + times + "]分钟才可以再发送。");
                    continue;
                }

                // 重新发送消息
                transactionMessageService.reSendMessage(message);

                log.info("结束处理[SENDING]消息ID为[" + message.getMessageId() + "]的消息");
            }catch (Exception e){
                log.error("处理[SENDING]消息ID为[" + message.getMessageId() + "]的消息异常：", e);
            }
        }

    }


}
