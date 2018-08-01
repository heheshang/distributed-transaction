package com.distributed.transaction;

import com.distributed.transaction.enums.message.NotifyDestinationNameEnum;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;


/**
 *
 */
@Component
@EnableScheduling
@Log4j2
public class Producer {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue queue;

 /*   @Autowired
    private Topic topic;*/

    private static int count = 0;
//
//    @Autowired
//    private JmsTemplate jmsTemplate;

    @Scheduled(fixedDelay = 3000)
    public void send() {

//        this.jmsMessagingTemplate.convertAndSend(this.queue, "hi.activeMQ,index=" + count);
//        this.jmsMessagingTemplate.convertAndSend(this.topic, "hi,activeMQ( topic )，index=" + count++);

        jmsTemplate.setDefaultDestinationName(NotifyDestinationNameEnum.BANK_NOTIFY.name());

        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {

                return session.createTextMessage("messageBody");
            }
        });

//        sendMessage("sssssssssss");


    }

 /*   private void sendMessage(final String message){

        log.info("{} " ,jmsTemplate.getDeliveryMode());

        jmsTemplate.convertAndSend("queue1",message);

    }*/
}
