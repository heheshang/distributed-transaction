package com.distributed.transaction.api.gateway.service.impl;

import com.distributed.transaction.api.gateway.service.core.AbstractTccGateWayRecord;
import com.distributed.transaction.api.gateway.vo.TccGatewayRecordVo;
import org.springframework.stereotype.Component;

/**
 * 微信充值测试
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-24-下午 2:50
 */
@Component("weChatPayGateWayService")
public class WeChatPayGateWayServiceImpl extends AbstractTccGateWayRecord {

    @Override
    public TccGatewayRecordVo handle(TccGatewayRecordVo vo) {

        return super.save(vo);
    }
}
