package com.distributed.transaction.service;

import com.distributed.transaction.api.GateWayReq;
import com.distributed.transaction.api.GateWayRes;
import com.distributed.transaction.gateway.BaseGatewatRechargeTransApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-13-下午 3:47
 */
@Component
public class TranService {

    @Autowired
    private BaseGatewatRechargeTransApi baseGatewatRechargeTransApi;

    public GateWayRes recharge(GateWayReq req) {

        return this.baseGatewatRechargeTransApi.recharge(req);
    }
}
