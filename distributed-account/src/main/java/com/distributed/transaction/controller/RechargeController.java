package com.distributed.transaction.controller;

import com.distributed.transaction.account.api.AccountReqT;
import com.distributed.transaction.account.api.AccountResT;
import com.distributed.transaction.service.ITranProcessApi;
import com.distributed.transaction.trade.api.recharge.RechargeMessage;
import com.distributed.transaction.trade.api.recharge.RechargeParam;
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
    public AccountResT<RechargeMessage> recharge(AccountReqT<RechargeParam> reqT) {


        return tranProcessApi.handle(reqT);
    }

}
