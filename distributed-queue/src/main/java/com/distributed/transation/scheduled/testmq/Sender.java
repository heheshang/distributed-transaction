package com.distributed.transation.scheduled.testmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Administrator on 2018/9/1.
 */

@Component
public class Sender {

    @Resource(name = "hospSyncRabbitTemplate")
    private RabbitTemplate hospSyncRabbitTemplate;

    @Resource(name = "hPayRabbitTemplate")
    private RabbitTemplate hPayRabbitTemplate;

    @Scheduled(cron = "0 0/1 * * * ? ")
    public void send1() {
        String context = "hello1 " + new Date();
        System.out.println("Sender : " + context);

        this.hospSyncRabbitTemplate.convertAndSend("topic.message","topic.message.routekey", context);
    }
    @Scheduled(cron = "0 0/1 * * * ? ")
    public void send2() {
        String context = "hello2 " + new Date();
        System.out.println("Sender : " + context);
        this.hPayRabbitTemplate.convertAndSend("trade.exchange","trade.exchange.routeKey", context);
    }

}
