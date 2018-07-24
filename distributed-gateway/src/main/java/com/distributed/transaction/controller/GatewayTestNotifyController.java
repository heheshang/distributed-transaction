package com.distributed.transaction.controller;


import com.distributed.transaction.gateway.api.GateWayReq;
import com.distributed.transaction.gateway.api.GateWayRes;
import com.distributed.transaction.api.gateway.service.core.AbstractTccGateWayRecord;
import com.distributed.transaction.api.gateway.vo.TccGatewayRecordVo;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模拟银行异步通知
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-13-下午 4:18
 */
@RestController
@RequestMapping("gateway")
public class GatewayTestNotifyController {

    @Autowired
    private AbstractTccGateWayRecord testPayGateWayService;

    @Autowired
    DozerBeanMapper mapper;

    @RequestMapping(value = "/recharge", method = RequestMethod.POST)
    public GateWayRes pay(@RequestBody GateWayReq req) {

        GateWayRes res = new GateWayRes();

        TccGatewayRecordVo vo = mapper.map(req.getT(), TccGatewayRecordVo.class);

        res.setR(testPayGateWayService.handle(vo));

        return res;
    }
}
