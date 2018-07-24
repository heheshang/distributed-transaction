package com.distributed.transaction.service.recharge;

import com.distributed.transaction.api.trans.ReqT;
import com.distributed.transaction.api.trans.ResT;
import com.distributed.transaction.api.trans.recharge.BaseRechargeTransApi;
import com.distributed.transaction.api.trans.recharge.RechargeMessage;
import com.distributed.transaction.api.trans.recharge.RechargeParam;
import com.distributed.transaction.service.BaseTranService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-04-下午 2:09
 */
@Component
@Log4j2
public class RechargeTranServiceImpl extends BaseTranService {

    @Autowired
    private BaseRechargeTransApi rechargeTransApi;

    @Override
    public void handle() {
        log.info("充值业务处理开始");
        ReqT reqT = new ReqT();

        RechargeParam param = new RechargeParam();

        param.setCustId("1111");
        param.setTxnTm(new Date());
        param.setPlatformId("order");
        param.setTransSeqNo(String.valueOf(new Random().nextInt(10000)));

        reqT.setParams(param);

        log.info("充值业务构建请求参数为 ,[{}]",reqT.toString());

        ResT<RechargeMessage> resT = this.rechargeTransApi.recharge(reqT);

        log.info("充值业务相应为 ,[{}]",resT.toString());
    }
}
