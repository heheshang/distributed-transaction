package com.distributed.transaction.controller;

import com.distributed.transaction.api.GateWayReq;
import com.distributed.transaction.api.GateWayRes;
import com.distributed.transaction.service.TranService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-13-下午 1:49
 */
@RestController
@Log4j2
public class TestNotifyController {

    @Autowired
    private TranService service;

    @PostMapping("/pay")
    public GateWayRes pay(@RequestParam Map<String, String> reqMap) {

        GateWayReq req = new GateWayReq();
        req.setT(reqMap);

        return service.recharge(req);
    }
}
