package com.distributed.transaction.controller;

import com.distributed.transaction.api.trans.ReqT;
import com.distributed.transaction.api.trans.ResT;
import com.distributed.transaction.api.trans.recharge.RechargeMessage;
import com.distributed.transaction.api.trans.recharge.RechargeParam;
import com.distributed.transaction.service.ITranProcessApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 充值
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-04-上午 11:23
 */
@RestController
public class RechargeController {

    @Autowired
    ITranProcessApi tranProcessApi;

    @PostMapping("/api/recharge")
    public ResT<RechargeMessage> recharge(ReqT<RechargeParam> reqT) {


        return tranProcessApi.handle(reqT);
    }

}
