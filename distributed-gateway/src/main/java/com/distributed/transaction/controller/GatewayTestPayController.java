package com.distributed.transaction.controller;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-13-下午 4:18
 */
@RestController
@RequestMapping("gateway")
public class GatewayTestPayController {

    @RequestMapping(value = "/recharge" ,method = RequestMethod.POST)
    public Map<String,String> pay(@RequestParam Map<String,String> map){


        return map;
    }
}
