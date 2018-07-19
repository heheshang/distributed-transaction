package com.distributed.transaction.service;

/**
 * 消息处理统一接口
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-05-下午 2:34
 */
public interface ITransMessage<TranReq, TranRes> {

    public TranRes handle(TranReq req) throws Exception;
}
