package com.distributed.transaction.service;

import com.distributed.transaction.BaseMessage;
import com.distributed.transaction.BaseParam;
import com.distributed.transaction.account.api.AccountReqT;
import com.distributed.transaction.account.api.AccountResT;

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
    public AccountResT<M> handle(AccountReqT<P> req);

}
