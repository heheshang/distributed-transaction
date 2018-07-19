package com.distributed.transaction;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-04-下午 4:05
 */
@Component
@EnableScheduling
@Log4j2
public class Producer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    private static int count = 0;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Scheduled(fixedDelay = 3000)
    public void send() {

        this.jmsMessagingTemplate.convertAndSend(this.queue, "hi.activeMQ,index=" + count);
        this.jmsMessagingTemplate.convertAndSend(this.topic, "hi,activeMQ( topic )，index=" + count++);

        sendMessage("sssssssssss");


    }

    private void sendMessage(final String message){

        log.info("{} " ,jmsTemplate.getDeliveryMode());

        jmsTemplate.convertAndSend("queue1",message);

    }
}
