package com.distributed.transaction.gateway.api;

import com.distributed.transaction.BaseParam;
import com.distributed.transaction.enums.PayTypeEnum;
import com.distributed.transaction.enums.PayWayEnum;
import lombok.Data;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-20-下午 2:29
 */
@Data
public class GateWayReq<T> {

    private T t;

    private PayWayEnum payWayEnum;

    private PayTypeEnum payTypeEnum;
}
