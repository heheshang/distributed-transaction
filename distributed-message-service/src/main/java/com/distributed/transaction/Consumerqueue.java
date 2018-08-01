package com.distributed.transaction;

import com.distributed.transaction.enums.message.NotifyDestinationNameEnum;
import lombok.extern.log4j.Log4j2;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-04-下午 4:06
 */
@Component
@Log4j2
public class Consumerqueue {
/*
    @JmsListener(destination = "distributed-topic", containerFactory = "jmsListenerContainerTopic")
    public void receiveTopic(String text) {

        System.out.println("Topic Consumer1:" + text);
    }

    @JmsListener(destination = "distributed-topic", containerFactory = "jmsListenerContainerTopic")
    public void receiveTopic2(String text) {

        System.out.println("Topic Consumer2:" + text);
    }

    @JmsListener(destination = "distributed-queue", containerFactory = "jmsListenerContainerQueue")
    public void reviceQueue(String text) {

        System.out.println("Queue Consumer:" + text);
    }*/

    @JmsListener(destination = "BANK_NOTIFY",containerFactory = "notifyBankJmsQueueListener")
    public void reciveQueue1(final TextMessage text, Session session) throws JMSException {
        try {



            log.info("Consumer收到的报文为:" + text.getText());
            text.acknowledge();// 使用手动签收模式，需要手动的调用，如果不在catch中调用session.recover()消息只会在重启服务后重发
        } catch (Exception e) {
            session.recover();// 此不可省略 重发信息使用
        }
    }
}
