package com.distributed.transation.scheduled.impl;

import com.distributed.transaction.enums.message.MessageStatusEnum;
import com.distributed.transaction.module.message.domain.TransactionMessageEntity;
import com.distributed.transation.scheduled.ITransactionMessageScheduled;
import com.distributed.transation.scheduled.biz.TransactionMessageService;
import com.google.common.collect.Maps;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Map;

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

    /**
     *
     */
    @Override
    public void handleWaitingConfirmTimeOutMessages() {

        Map<String, Object> qureyMap = Maps.newHashMap();

        qureyMap.put("status", MessageStatusEnum.WAITING_CONFIRM);

        int pageNum = 0;

        int pageSize = 20;

        Page<TransactionMessageEntity> page = transactionMessageService.getTransactionMessage(pageNum, pageSize, qureyMap);

        log.info("待发送消息数量====>[{}]", page.getTotalPages());
    }

    @Override
    public void handleSendingTimeOutMessages() {

        Map<String, Object> qureyMap = Maps.newHashMap();

        qureyMap.put("status", MessageStatusEnum.SENDING);
        int pageNum = 0;
        int pageSize = 20;



        Page<TransactionMessageEntity> page = transactionMessageService.getTransactionMessage(pageNum, pageSize, qureyMap);

        log.info("待发送消息数量====>[{}]", page.getTotalPages());

        Page<TransactionMessageEntity> page1 = transactionMessageService.getTransactionMessage(1, pageSize, qureyMap);

        log.info("待发送消息数量1====>[{}]", page1.getTotalPages());
    }


}
