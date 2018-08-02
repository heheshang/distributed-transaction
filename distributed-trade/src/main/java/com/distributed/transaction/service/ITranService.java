package com.distributed.transaction.service;

import com.distributed.transaction.BaseMessage;
import com.distributed.transaction.BaseParam;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-04-下午 2:01
 */
public interface ITranService<P extends BaseParam, M extends BaseMessage> {

    M handle(P p);


}
