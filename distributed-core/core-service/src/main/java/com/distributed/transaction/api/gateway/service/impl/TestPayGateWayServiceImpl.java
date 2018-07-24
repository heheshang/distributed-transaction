package com.distributed.transaction.api.gateway.service.impl;

import com.distributed.transaction.api.gateway.service.core.AbstractTccGateWayRecord;
import com.distributed.transaction.api.gateway.vo.TccGatewayRecordVo;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * 模拟测试业务
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-24-下午 2:44
 */
@Log4j2
@Component("testPayGateWayService")
public class TestPayGateWayServiceImpl extends AbstractTccGateWayRecord {

    @Override
    public TccGatewayRecordVo handle(TccGatewayRecordVo vo) {

        return super.save(vo);
    }
}
