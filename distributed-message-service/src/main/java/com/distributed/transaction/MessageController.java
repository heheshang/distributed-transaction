package com.distributed.transaction;

import com.distributed.transaction.enums.message.MessageStatusEnum;
import com.distributed.transaction.exception.MessageBizException;
import com.distributed.transaction.message.api.MessageReqT;
import com.distributed.transaction.module.message.domain.TransactionMessageEntity;
import com.distributed.transaction.module.message.repository.TransactionMessageRepository;
import com.google.common.collect.Maps;
import lombok.extern.log4j.Log4j2;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.Date;
import java.util.Map;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-08-上午 10:05
 */
@RestController
@RequestMapping("/message")
@Log4j2
public class MessageController {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private DozerBeanMapper mapper;

    @Autowired
    private TransactionMessageRepository transactionMessageRepository;

    @PostMapping("/confirm/send")
    public void confirmAndSend(@RequestBody MessageReqT messageReq) {

        log.info("消息服务接收到待确认消息请求【{}】",messageReq.toString());

        Map<String,String> map = Maps.newHashMap();
        map.putAll((Map<? extends String, ? extends String>) messageReq.getParam());

        final TransactionMessageEntity messageEntity = transactionMessageRepository.getByMessageId(map.get("messageId"));

        if (messageEntity == null) {

            log.error("通过消息Id【{}】,获取消息记录为空");
            throw new MessageBizException(MessageBizException.MESSAGE_SYSTEM_ERROR, "根据消息id查找的消息为空");
        }


        messageEntity.setStatus(MessageStatusEnum.SENDING);

        messageEntity.setEditTime(new Date());

        jmsTemplate.setDefaultDestinationName(messageEntity.getConsumerQueue().name());

        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {

                return session.createTextMessage(messageEntity.getMessageBody());
            }
        });

    }

}
