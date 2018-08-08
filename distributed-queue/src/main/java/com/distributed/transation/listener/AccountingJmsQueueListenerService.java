package com.distributed.transation.listener;

import com.distributed.transaction.accounting.BaseAccountIngServiceApi;
import com.distributed.transaction.accounting.api.AccountingReqT;
import com.distributed.transaction.module.accounting.vo.AccountingVoucher;
import com.distributed.transaction.utils.SingletonGsonEnum;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @author ssk www.distributed.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-02-下午 2:21
 */
@Component
@Log4j2
public class AccountingJmsQueueListenerService {

    @Autowired
    private BaseAccountIngServiceApi baseAccountIngServiceApi;


    @JmsListener(destination = "ACCOUNTING_NOTIFY", containerFactory = "accountingQueueJmsListener")
    public synchronized void reciveAccountingMessage(final TextMessage message, Session session) throws JMSException {

        try {

            log.info("收到会计凭证消息队列信息【{}】", message.getText());

            AccountingVoucher voucher = SingletonGsonEnum.instences.fromJson(message.getText(), AccountingVoucher.class);

            AccountingReqT accountingReq = new AccountingReqT();

            accountingReq.setParam(voucher);

            baseAccountIngServiceApi.createAccountVoucher(accountingReq);


            message.acknowledge();// 使用手动签收模式，需要手动的调用，如果不在catch中调用session.recover()消息只会在重启服务后重发

        } catch (Exception e) {

            session.recover();// 此不可省略 重发信息使用
        }
    }
}
