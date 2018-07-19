package com.distributed.transaction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 测试控制器
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-13-上午 11:16
 */
@Controller

public class TestPayController {

    @RequestMapping("/testpay")
    public String testPay() {

        return "pay";
    }



}
