package com.distributed.transaction.gateway;

import com.distributed.transaction.gateway.api.GateWayReq;
import com.distributed.transaction.gateway.api.GateWayRes;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 充值交易接口
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-04-上午 10:52
 */
@FeignClient("DISTRIBUTED-GATEWAY")
public interface BaseGatewatRechargeTransApi {

    /**
     * 充值
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "/gateway/recharge", method = RequestMethod.POST)
    public GateWayRes recharge(GateWayReq req);
}
