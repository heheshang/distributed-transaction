package com.distributed.transaction.service.impl;

import com.distributed.transaction.gateway.api.GateWayReq;
import com.distributed.transaction.gateway.api.GateWayRes;
import com.distributed.transaction.module.gateway.vo.TccGatewayRecord;
import com.distributed.transaction.service.core.AbstractTccGateWayRecord;
import com.distributed.transaction.service.core.ITccGateWayRecordService;
import com.distributed.transaction.trade.api.TradeReq;
import com.distributed.transaction.trade.api.recharge.RechargeParam;
import org.springframework.stereotype.Component;

/**
 * 微信充值测试
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-24-下午 2:50
 */
@Component("weChatPayGateWayService")
public class WeChatPayGateWayServiceImpl extends AbstractTccGateWayRecord implements ITccGateWayRecordService {

    @Override
    public GateWayRes handle(GateWayReq gateWayReq) {

        TccGatewayRecord gatewayRecord = this.mapper.map(gateWayReq.getT(), TccGatewayRecord.class);

        gatewayRecord = super.save(gatewayRecord);

        TradeReq req = new TradeReq();

        RechargeParam rechargeParam = new RechargeParam();

        rechargeParam.setTransSeqNo(gatewayRecord.getId());

        req.setParams(rechargeParam);

//        tradeRechargeTransApi.recharge(req);
        GateWayRes res = new GateWayRes();

        res.setMessage(gatewayRecord);

        return res;
    }

    @Override
    public TccGatewayRecord save(TccGatewayRecord vo) {

        return super.save(vo);
    }
}
