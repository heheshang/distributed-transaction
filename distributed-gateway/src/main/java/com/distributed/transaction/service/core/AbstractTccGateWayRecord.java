package com.distributed.transaction.service.core;

import com.distributed.transaction.enums.message.AreadlyDeadEnum;
import com.distributed.transaction.enums.message.MessageStatusEnum;
import com.distributed.transaction.enums.message.MsgDataTypeEnum;
import com.distributed.transaction.enums.message.NotifyDestinationNameEnum;
import com.distributed.transaction.module.gateway.domain.TccGatewayRecordEntity;
import com.distributed.transaction.module.gateway.repository.TccGatewayRecordRepository;
import com.distributed.transaction.module.gateway.vo.TccGatewayRecord;
import com.distributed.transaction.module.message.domain.TransactionMessageEntity;
import com.distributed.transaction.module.message.repository.TransactionMessageRepository;
import com.distributed.transaction.module.message.vo.TransactionMessage;
import com.distributed.transaction.utils.SingletonGsonEnum;
import lombok.extern.log4j.Log4j2;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-24-下午 2:39
 */
@Log4j2
public abstract class AbstractTccGateWayRecord {

    @Autowired
    private TccGatewayRecordRepository tccGatewayRecordRepository;

    @Autowired
    private TransactionMessageRepository transactionMessageRepository;

    @Autowired
    public DozerBeanMapper mapper;

    @Autowired
    public JmsTemplate jmsTemplate;

    @Transactional(rollbackFor = Exception.class)
    public TccGatewayRecord save(TccGatewayRecord vo) {

        log.info("保存网关流水记录");

        TccGatewayRecordEntity record = mapper.map(vo, TccGatewayRecordEntity.class);

        return mapper.map(tccGatewayRecordRepository.save(record), TccGatewayRecord.class);
    }

    @Transactional
    public TccGatewayRecord update(TccGatewayRecord vo) {

        log.info("更新网关流水记录");

        TccGatewayRecordEntity record = tccGatewayRecordRepository.findByOrderNoAndPayKey(vo.getOrderNo(), vo.getPayKey());

        return mapper.map(tccGatewayRecordRepository.save(record), TccGatewayRecord.class);
    }

    @Transactional(rollbackFor = Exception.class)
    public int saveMessage(Map<String, String> notifyMap) {


        TransactionMessage message = new TransactionMessage();

        String messageId = UUID.randomUUID().toString().replace("-", "");

        notifyMap.put("messageId", messageId);

        String messageBody = SingletonGsonEnum.instences.getGson().toJson(notifyMap);


        message.setEditor("gateway");
        message.setCreater("gateway");
        message.setEditTime(new Date());
        message.setCreateTime(new Date());
        message.setMessageId(messageId);
        message.setMessageBody(messageBody);
        message.setMsgDataType(MsgDataTypeEnum.JSON);
        message.setConsumerQueue(NotifyDestinationNameEnum.BANK_NOTIFY);
        message.setMessageSendTimes(0);
        message.setAreadlyDead(AreadlyDeadEnum.NO);
        message.setStatus(MessageStatusEnum.SENDING);
        message.setRemark("");
        message.setField1("");
        message.setField2("");
        message.setField3("");


        TransactionMessageEntity result = transactionMessageRepository.save(mapper.map(message, TransactionMessageEntity.class));

        jmsTemplate.setDefaultDestinationName(NotifyDestinationNameEnum.BANK_NOTIFY.name());

        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {

                return session.createTextMessage(messageBody);
            }
        });

        if (result != null) {
            return 1;
        } else {
            return 0;
        }

    }
}
