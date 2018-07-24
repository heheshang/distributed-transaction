package com.distributed.transaction.api.gateway.service.core;

import com.distributed.transaction.api.gateway.vo.TccGatewayRecordVo;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-24-下午 2:37
 */
public interface ITccGateWayRecordService {

    TccGatewayRecordVo save(TccGatewayRecordVo vo);
}
