package com.distributed.transaction.service.impl;

import com.distributed.transaction.module.gateway.vo.TccGatewayRecordVo;
import com.distributed.transaction.gateway.api.GateWayRes;
import com.distributed.transaction.service.core.AbstractTccGateWayRecord;
import com.distributed.transaction.service.core.ITccGateWayRecordService;
import com.distributed.transaction.trade.BaseTradeRechargeTransApi;
import com.distributed.transaction.trade.api.TradeReq;
import com.distributed.transaction.trade.api.TradeRes;
import com.distributed.transaction.trade.api.recharge.RechargeMessage;
import com.distributed.transaction.trade.api.recharge.RechargeParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public GateWayRes handle(TccGatewayRecordVo vo) {

        vo = super.save(vo);
        TradeReq req = new TradeReq();
        RechargeParam rechargeParam = new RechargeParam();
        rechargeParam.setTransSeqNo(vo.getId());
        req.setParams(rechargeParam);
        TccGatewayRecordVo vo1 = new TccGatewayRecordVo();
        vo1.setOrderNo("pt1982018072509140000");
        vo1.setPayKey("4c52295065654407b42797cda80dd07d");
        super.update(vo1);

        TradeRes<RechargeMessage> tradeRes = tradeRechargeTransApi.recharge(req);

        GateWayRes res = new GateWayRes();

        res.setMessage(vo);

        res.setSuccess(Boolean.TRUE);

        return res;
    }

    @Override
    public TccGatewayRecordVo save(TccGatewayRecordVo vo) {

        return super.save(vo);
    }
}
