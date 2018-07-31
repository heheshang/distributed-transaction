package com.distributed.transaction.service.recharge;

import com.distributed.transaction.annotations.TradeTransType;
import com.distributed.transaction.service.ITranService;
import com.distributed.transaction.trade.BaseTradeRechargeTransApi;
import com.distributed.transaction.trade.api.TradeReq;
import com.distributed.transaction.trade.api.TradeRes;
import com.distributed.transaction.trade.api.recharge.RechargeMessage;
import com.distributed.transaction.trade.api.recharge.RechargeParam;
import com.distributed.transaction.utils.PayTypeEnum;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Random;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-04-下午 2:09
 */

@Log4j2
@TradeTransType(value = PayTypeEnum.ALI_TEST)
public class AliPayTranServiceImpl implements ITranService<RechargeParam, RechargeMessage> {

    @Autowired
    private BaseTradeRechargeTransApi rechargeTransApi;


    @Override
    public RechargeMessage handle(RechargeParam param) {

        log.info("支付宝充值业务处理开始");
        TradeReq reqT = new TradeReq();

        param.setMerchantOrderNo("1111");
        param.setTxnTm(new Date());
        param.setTransSeqNo(String.valueOf(new Random().nextInt(10000)));

        reqT.setParams(param);

        log.info("充值业务构建请求参数为 ,[{}]", reqT.toString());

        TradeRes<RechargeMessage> resT = this.rechargeTransApi.recharge(reqT);

        log.info("充值业务相应为 ,[{}]", resT.toString());
        return null;
    }


}
