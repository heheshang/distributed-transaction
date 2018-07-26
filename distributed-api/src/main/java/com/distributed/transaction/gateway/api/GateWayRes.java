package com.distributed.transaction.gateway.api;

import com.distributed.transaction.BaseMessage;
import lombok.Data;
import lombok.ToString;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-20-下午 2:29
 */
@Data
@ToString
public class GateWayRes<M > {

    private M message;

    private Boolean success;

    private String procCode;

    private String procDesc;

}
