package com.distributed.transaction.service;

import com.distributed.transaction.trade.api.BaseMessage;
import com.distributed.transaction.trade.api.BaseParam;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-04-下午 2:01
 */
public interface ITranService<P extends BaseParam,R extends BaseMessage> {

    public R handle(P p);
}
