package com.distributed.transation.listener;

import com.distributed.transaction.utils.SingletonGsonEnum;
import com.distributed.transation.bankmessage.BankMessageTask;
import com.distributed.transation.bankmessage.biz.BankMessageService;
import com.google.common.collect.Maps;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.Map;

/**
 * @author ssk www.distributed.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-02-下午 2:21
 */
@Component
@Log4j2
public class BankJmsQueueListenerService {

    @Autowired
    private ThreadPoolTaskExecutor threadPool;
    @Autowired
    private BankMessageService bankMessageService;


    @JmsListener(destination = "BANK_NOTIFY", containerFactory = "bankQueueJmsListener")
    public synchronized void reciveBankMessage(final TextMessage message, Session session) throws JMSException {

        try {

            Map<String, String> retMap = Maps.newHashMap();

            retMap = SingletonGsonEnum.instences.fromJson(message.getText(), retMap.getClass());

            BankMessageTask task = new BankMessageTask(retMap);

            task.setBankMessageService(bankMessageService);

            threadPool.execute(task);


            log.info("BANK_NOTIFY:" + message.getText());

            message.acknowledge();// 使用手动签收模式，需要手动的调用，如果不在catch中调用session.recover()消息只会在重启服务后重发

        } catch (Exception e) {

            session.recover();// 此不可省略 重发信息使用
        }
    }
}
