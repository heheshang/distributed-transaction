package com.distributed.transaction.controller;

import com.distributed.transaction.gateway.api.GateWayReq;
import com.distributed.transaction.gateway.api.GateWayRes;
import com.distributed.transaction.service.core.ITccGateWayRecordService;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
public class GatewayTestPayController {

    @Autowired
    private ITccGateWayRecordService testPayGateWayService;

    @Autowired
    DozerBeanMapper mapper;

    @RequestMapping(value = "/recharge", method = RequestMethod.POST)
    public GateWayRes pay(@RequestBody  GateWayReq req) {

        log.info("gateway 收到请求【{}】",req.getT());

        return testPayGateWayService.handle(req);
    }
}
