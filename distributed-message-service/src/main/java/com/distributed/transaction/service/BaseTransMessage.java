package com.distributed.transaction.service;

import com.distributed.transaction.tran.TranReq;
import com.distributed.transaction.tran.TranRes;

/**
 * 消息处理实现
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-05-下午 2:35
 */
public abstract class BaseTransMessage implements ITransMessage<TranReq, TranRes> {


    @Override
    public TranRes handle(TranReq req) {

        TranRes res = new TranRes();

        try {
            return process(req);
        } catch (Exception e) {

        }
        return null;
    }


    public abstract TranRes process(TranReq req);
}
