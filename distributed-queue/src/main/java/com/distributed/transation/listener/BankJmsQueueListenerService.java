package com.distributed.transation.listener;

import lombok.extern.log4j.Log4j2;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-02-下午 2:21
 */
@Component
@Log4j2
public class BankJmsQueueListenerService {

    @JmsListener(destination = "BANK_NOTIFY", containerFactory = "bankQueueJmsListener")
    public void reciveMessage(final TextMessage text, Session session) throws JMSException {

        try {


            log.info("BANK_NOTIFY:" + text.getText());
            text.acknowledge();// 使用手动签收模式，需要手动的调用，如果不在catch中调用session.recover()消息只会在重启服务后重发
        } catch (Exception e) {
            session.recover();// 此不可省略 重发信息使用
        }
    }
}
