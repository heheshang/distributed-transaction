package com.distributed.transaction.service.recharge;

import com.distributed.transaction.register.TransType;
import com.distributed.transaction.register.TransTypeEnum;
import com.distributed.transaction.service.ITranService;
import com.distributed.transaction.trade.api.recharge.RechargeMessage;
import com.distributed.transaction.trade.api.recharge.RechargeParam;
import lombok.extern.log4j.Log4j2;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-25-下午 3:04
 */
@TransType(value = TransTypeEnum.TEST_RECHARGE_PAY)
@Log4j2
public class TestPayTranServiceImpl implements ITranService<RechargeParam, RechargeMessage> {


    @Override
    public RechargeMessage handle(RechargeParam param) {

        log.info("虚拟充值测试服务执行");
        return null;
    }
}
