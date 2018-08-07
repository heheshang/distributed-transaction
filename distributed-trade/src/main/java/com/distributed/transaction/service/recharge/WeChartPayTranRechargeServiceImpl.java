package com.distributed.transaction.service.recharge;

import com.distributed.transaction.annotations.TradeTransType;
import com.distributed.transaction.service.ITranService;
import com.distributed.transaction.trade.api.recharge.RechargeMessage;
import com.distributed.transaction.trade.api.recharge.RechargeParam;
import com.distributed.transaction.enums.PayTypeEnum;
import lombok.extern.log4j.Log4j2;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-25-下午 3:04
 */
@TradeTransType(value = PayTypeEnum.WEIXIN_SCANPAY)
@Log4j2
public class WeChartPayTranRechargeServiceImpl implements ITranService<RechargeParam, RechargeMessage> {


    @Override
    public RechargeMessage handle(RechargeParam baseParam) {

        return null;
    }


}
