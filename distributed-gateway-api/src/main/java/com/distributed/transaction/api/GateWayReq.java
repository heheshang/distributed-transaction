package com.distributed.transaction.api;

import lombok.Data;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-20-下午 2:29
 */
@Data
public class GateWayReq<T> {

    private T t;
}
