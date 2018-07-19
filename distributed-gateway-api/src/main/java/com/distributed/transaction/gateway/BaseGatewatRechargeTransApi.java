package com.distributed.transaction.gateway;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

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
     * @param params
     * @return
     */
    @RequestMapping(value = "/gateway/recharge", method = RequestMethod.POST)
    public Map<String, String> recharge(@RequestParam  Map<String, String> params);
}
