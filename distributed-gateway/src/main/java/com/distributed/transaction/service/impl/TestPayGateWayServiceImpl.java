package com.distributed.transaction.service.impl;

import com.distributed.transaction.enums.PayTypeEnum;
import com.distributed.transaction.enums.PayWayEnum;
import com.distributed.transaction.gateway.api.GateWayRes;
import com.distributed.transaction.module.gateway.vo.TccGatewayRecord;
import com.distributed.transaction.service.core.AbstractTccGateWayRecord;
import com.distributed.transaction.service.core.ITccGateWayRecordService;
import com.distributed.transaction.trade.BaseTradeRechargeTransApi;
import com.distributed.transaction.trade.api.TradeReq;
import com.distributed.transaction.trade.api.TradeRes;
import com.distributed.transaction.trade.api.recharge.RechargeMessage;
import com.distributed.transaction.trade.api.recharge.RechargeParam;
import com.google.common.collect.Maps;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 模拟测试业务
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-24-下午 2:44
 */
@Log4j2
@Component("testPayGateWayService")
public class TestPayGateWayServiceImpl extends AbstractTccGateWayRecord implements ITccGateWayRecordService {

    @Autowired
    private BaseTradeRechargeTransApi tradeRechargeTransApi;

    @Override
    public GateWayRes handle(TccGatewayRecord vo) {

        vo = super.save(vo);

        TradeReq req = new TradeReq();

        RechargeParam rechargeParam = new RechargeParam();

        rechargeParam.setTransSeqNo(vo.getId());

        rechargeParam.setMerchantOrderNo(vo.getOrderNo());

        rechargeParam.setPayKey(vo.getPayKey());

        rechargeParam.setPayTypeCode(PayTypeEnum.TEST_PAY_HTTP_CLIENT.getWay());

        rechargeParam.setPayWayCode(PayWayEnum.TEST_PAY_HTTP_CLIENT.name());

        rechargeParam.setOrderAmount(new BigDecimal(vo.getOrderPrice()));

        rechargeParam.setOrderIp(vo.getOrderIp());

//        rechargeParam.setExpireTime();

        req.setParams(rechargeParam);
     /*   TccGatewayRecord vo1 = new TccGatewayRecord();
        vo1.setOrderNo("pt1982018072509140000");
        vo1.setPayKey("4c52295065654407b42797cda80dd07d");
        super.update(vo1);
*/
        TradeRes<RechargeMessage> tradeRes = tradeRechargeTransApi.recharge(req);

        GateWayRes res = new GateWayRes();

        Map<String, String> resmap = Maps.newHashMap();

        resmap.put("bankOrderNo", tradeRes.getMessage().getBankOrderNo());
        resmap.put("merchantNo", tradeRes.getMessage().getMerchantNo());
        resmap.put("merchantOrderNo", tradeRes.getMessage().getMerchantOrderNo());
        resmap.put("orderAmount", String.valueOf(tradeRes.getMessage().getOrderAmount()));
        resmap.put("status", tradeRes.getMessage().getStatus());
        resmap.put("trxNo", tradeRes.getMessage().getTrxNo());

        res.setMessage(resmap);

        res.setSuccess(tradeRes.getSuccess());

        return res;
    }

    @Override
    public TccGatewayRecord save(TccGatewayRecord vo) {

        return super.save(vo);
    }
}
