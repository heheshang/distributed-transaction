package com.distributed.transaction.service.core;

import com.distributed.transaction.module.gateway.vo.TccGatewayRecordVo;
import com.distributed.transaction.gateway.api.GateWayRes;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-24-下午 2:37
 */
public interface ITccGateWayRecordService {

    GateWayRes handle(TccGatewayRecordVo vo);
}
