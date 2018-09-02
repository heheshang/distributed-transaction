package com.distributed.transation.listener;

import com.distributed.transaction.utils.SingletonGsonEnum;
import com.distributed.transation.bankmessage.BankMessageTask;
import com.distributed.transation.bankmessage.biz.BankMessageService;
import com.google.common.collect.Maps;
import com.rabbitmq.client.Channel;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author ssk www.distributed.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-02-下午 2:21
 */
@Component
@Log4j2
public class BankJmsQueueListenerService implements ChannelAwareMessageListener {

    @Autowired
    private ThreadPoolTaskExecutor threadPool;

    @Autowired
    private BankMessageService bankMessageService;


   /* @JmsListener(destination = "BANK_NOTIFY", containerFactory = "bankQueueJmsListener")
    public synchronized void reciveBankMessage(final TextMessage message, Session session) throws JMSException {

        try {

            log.info("【监听银行通知消息队列信息】,【{}】", message.getText());

            Map<String, String> retMap = Maps.newHashMap();

            retMap = SingletonGsonEnum.instences.fromJson(message.getText(), retMap.getClass());

            BankMessageTask task = new BankMessageTask(retMap);

            task.setBankMessageService(bankMessageService);

            threadPool.execute(task);

            log.info("BANK_NOTIFY:" + message.getText());

            message.acknowledge();// 使用手动签收模式，需要手动的调用，如果不在catch中调用session.recover()消息只会在重启服务后重发

        } catch (Exception e) {
            log.error("【监听银行消息队列信息异常啦】,【{}】", e.getMessage());
//            session.recover();// 此不可省略 重发信息使用
        }
    }*/

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try {

            //开启事物
//            channel.txSelect();

            log.info("【监听银行通知消息队列信息】,【{}】", message.getBody());

            Map<String, String> retMap = Maps.newHashMap();

            retMap = SingletonGsonEnum.instences.fromJson(new String(message.getBody()), retMap.getClass());

            BankMessageTask task = new BankMessageTask(retMap);

            task.setBankMessageService(bankMessageService);

            threadPool.execute(task);

            log.info("BANK_NOTIFY:" + new String(message.getBody()));
            // 手动确认模式
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            // channel.basicPublish(EXCHANGES_BANK_NOTIFY, ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBody());
            // 提交事物
            //channel.txCommit();
        } catch (Exception e) {
            log.error("【监听银行消息队列信息异常啦】,【{}】", e.getMessage());
            //回滚
            // channel.txRollback();
            ////ack返回false，并重新回到队列，api里面解释得很清楚
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }
}
