package com.distributed.transaction.api.gateway.service;

import com.distributed.transaction.api.gateway.vo.TccGatewayRecordVo;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-20-下午 2:00
 */
public interface ITccGatewayRecordService {

    public TccGatewayRecordVo save(TccGatewayRecordVo vo);
}
