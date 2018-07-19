package com.distributed.transaction.service;

import com.distributed.transaction.tran.TranReq;
import com.distributed.transaction.tran.TranRes;

/**
 * 消息操作接口
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-06-上午 11:02
 */
public interface IOptMessage {

    public TranRes process(TranReq req, TranRes res);
}
