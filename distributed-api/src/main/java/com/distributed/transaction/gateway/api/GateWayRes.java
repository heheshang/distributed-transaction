package com.distributed.transaction.gateway.api;

import lombok.Data;
import lombok.ToString;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-20-下午 2:29
 */
@Data
@ToString
public class GateWayRes<R> {

    private R r;

    private String data;
}
