package com.distributed.transaction.service;

import com.distributed.transaction.api.trans.BaseMessage;
import com.distributed.transaction.api.trans.BaseParam;
import com.distributed.transaction.api.trans.ReqT;
import com.distributed.transaction.api.trans.ResT;

/**
 * 交易统一入口
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-04-上午 11:34
 */
public interface ITranProcessApi<P extends BaseParam, M extends BaseMessage> {

    /**
     * 交易处理入口
     *
     * @param req
     * @return
     */
    public ResT<M> handle(ReqT<P> req);

}
