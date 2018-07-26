package com.distributed.transaction.service.impl;

import com.distributed.transaction.module.gateway.vo.TccGatewayRecordVo;
import com.distributed.transaction.gateway.api.GateWayRes;
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
    public GateWayRes handle(TccGatewayRecordVo vo) {

        vo = super.save(vo);

        TradeReq req = new TradeReq();

        RechargeParam rechargeParam = new RechargeParam();

        rechargeParam.setTransSeqNo(vo.getId());

        req.setParams(rechargeParam);

//        tradeRechargeTransApi.recharge(req);
        GateWayRes res = new GateWayRes();

        res.setMessage(vo);

        return res;
    }

    @Override
    public TccGatewayRecordVo save(TccGatewayRecordVo vo) {

        return super.save(vo);
    }
}
