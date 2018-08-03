package com.distributed.transation.scheduled.impl;

import com.distributed.transation.scheduled.ITransactionMessageScheduled;
import org.springframework.stereotype.Component;

/**
 * 超时消息处理业务处理业务实现
 *
 * @author ssk www.distributed.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-02-下午 5:03
 */
@Component("transactionMessageScheduled")
public class TransactionMessageScheduledImpl implements ITransactionMessageScheduled {

    @Override
    public void handleWaitingConfirmTimeOutMessages() {

    }

    @Override
    public void handleSendingTimeOutMessages() {

    }


}
