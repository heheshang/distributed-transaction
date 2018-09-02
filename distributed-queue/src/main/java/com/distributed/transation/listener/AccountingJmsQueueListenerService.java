package com.distributed.transation.listener;

import com.distributed.transaction.accounting.BaseAccountIngServiceApi;
import com.distributed.transaction.accounting.api.AccountingReqT;
import com.distributed.transaction.module.accounting.vo.AccountingVoucher;
import com.distributed.transaction.utils.SingletonGsonEnum;
import com.distributed.transation.scheduled.biz.TransactionMessageService;
import com.rabbitmq.client.Channel;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ssk www.distributed.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-02-下午 2:21
 */
@Component
@Log4j2
public class AccountingJmsQueueListenerService implements ChannelAwareMessageListener {

    @Autowired
    private BaseAccountIngServiceApi baseAccountIngServiceApi;

    @Autowired
    private TransactionMessageService transactionMessageService;


    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try {

            log.info("收到会计凭证消息队列信息【{}】", message.getBody());

            AccountingVoucher voucher = SingletonGsonEnum.instences.getGson().fromJson(String.valueOf(message.getBody()), AccountingVoucher.class);

            AccountingReqT accountingReq = new AccountingReqT();

            accountingReq.setParam(voucher);

            baseAccountIngServiceApi.createAccountVoucher(accountingReq);

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

            transactionMessageService.deleteMessageByMessageId(voucher.getMessageId());

        } catch (Exception e) {

            log.error("【发送会计凭证消息队列信息异常啦】,【{}】", e);
            ////ack返回false，并重新回到队列，api里面解释得很清楚
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }
}
