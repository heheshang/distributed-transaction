package com.distributed.transation.scheduled;

/**
 * 超时消息处理服务
 *
 * @author ssk www.distributed.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-02-下午 5:01
 */
public interface ITransactionMessageScheduled  {

    /**
     * 处理状态为"待确认" 但已超时的消息
     */
    void handleWaitingConfirmTimeOutMessages();

    /**
     * 处理状态为"发送中" 但已超时的消息
     */
    void handleSendingTimeOutMessages();


}
