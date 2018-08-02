package com.distributed.transaction.controller;

import com.distributed.transaction.gateway.api.GateWayReq;
import com.distributed.transaction.gateway.api.GateWayRes;
import com.distributed.transaction.module.gateway.vo.TccGatewayRecord;
import com.distributed.transaction.service.core.ITccGateWayRecordService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-13-下午 4:18
 */
@RestController
@RequestMapping("gateway")
public class GatewayTestPayController {

    @Autowired
    private ITccGateWayRecordService testPayGateWayService;

    @Autowired
    DozerBeanMapper mapper;

    @RequestMapping(value = "/recharge", method = RequestMethod.POST)
    public GateWayRes pay(@RequestBody  GateWayReq req) {

        GateWayRes res = new GateWayRes();

        res.setMessage(testPayGateWayService.handle(req));

        return res;
    }
}
