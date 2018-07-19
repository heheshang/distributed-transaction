package com.distributed.transaction.api.trans.recharge;

import com.distributed.transaction.api.trans.ReqT;
import com.distributed.transaction.api.trans.ResT;
import com.distributed.transaction.api.trans.TransApi;
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
@FeignClient("DISTRIBUTED-ACCOUNT")
public interface BaseRechargeTransApi extends TransApi {

    /**
     * 充值
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/api/recharge", method = RequestMethod.POST)
    public ResT<RechargeMessage> recharge(ReqT<RechargeParam> params);
}
