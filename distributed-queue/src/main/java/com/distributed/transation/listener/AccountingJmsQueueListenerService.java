package com.distributed.transation.listener;

import com.distributed.transaction.accounting.BaseAccountIngServiceApi;
import com.distributed.transaction.accounting.api.AccountingReqT;
import com.distributed.transaction.module.accounting.vo.AccountingVoucher;
import lombok.extern.log4j.Log4j2;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;

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
    @Autowired
    private DozerBeanMapper mapper;

    @JmsListener(destination = "ACCOUNTING_NOTIFY", containerFactory = "accountingQueueJmsListener")
    public synchronized void reciveAccountingMessage(final ObjectMessage message, Session session) throws JMSException {

        try {

            log.info("收到会计凭证消息队列信息【{}】", message);

            AccountingVoucher voucher = mapper.map(message.getObject(), AccountingVoucher.class);

            AccountingReqT accountingReq = new AccountingReqT();

            accountingReq.setParam(voucher);

            boolean issuccess = baseAccountIngServiceApi.createAccountVoucher(accountingReq);

            if (issuccess) {
                message.acknowledge();// 使用手动签收模式，需要手动的调用，如果不在catch中调用session.recover()消息只会在重启服务后重发
            }else {

                log.error("【发送会计凭证消息队列信息异常啦】,【{}】", issuccess);
                session.recover();// 此不可省略 重发信息使用
            }

        } catch (Exception e) {

            log.error("【发送会计凭证消息队列信息异常啦】,【{}】", e);
            session.recover();// 此不可省略 重发信息使用
        }
    }
}
