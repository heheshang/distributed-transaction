package com.distributed.transaction.controller;


import com.distributed.transaction.gateway.api.GateWayReq;
import com.distributed.transaction.gateway.api.GateWayRes;
import com.distributed.transaction.service.core.ITccGateWayRecordService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
    private ITccGateWayRecordService testNotifyGateWayService;

    @Autowired
    DozerBeanMapper mapper;

    @RequestMapping(value = "/notify/{payWayCode}", method = RequestMethod.POST )
    public GateWayRes pay(@PathVariable("payWayCode") String payWayCode, @RequestBody GateWayReq req) {

        GateWayRes res = new GateWayRes();

      /*  TccGatewayRecordEntity vo = mapper.map(req.getT(), TccGatewayRecordEntity.class);

        res.setMessage(testPayGateWayService.handle(vo));*/

        res = testNotifyGateWayService.handle(req);

        return res;
    }
}
