package com.distributed.transaction.gateway;

import com.distributed.transaction.gateway.api.GateWayReq;
import com.distributed.transaction.gateway.api.GateWayRes;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 充值交易接口
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-04-上午 10:52
 */
@FeignClient("DISTRIBUTED-GATEWAY")
public interface BaseGatewayRechargeTransApi {

    /**
     * 充值
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "/gateway/recharge", method = RequestMethod.POST)
    public GateWayRes recharge(GateWayReq req);

    /**
     * 银行异步通知
     * @param req
     * @return
     */
    @RequestMapping(value = "/gateway/notify/{payWayCode}", method = RequestMethod.POST)
    public GateWayRes bankNotify(@PathVariable("payWayCode") String payWayCode, GateWayReq req);
}
