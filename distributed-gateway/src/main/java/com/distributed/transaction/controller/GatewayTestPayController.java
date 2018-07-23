package com.distributed.transaction.controller;

import com.distributed.transaction.api.GateWayReq;
import com.distributed.transaction.api.GateWayRes;
import com.distributed.transaction.api.gateway.service.ITccGatewayRecordService;
import com.distributed.transaction.api.gateway.vo.TccGatewayRecordVo;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-13-下午 4:18
 */
@RestController
@RequestMapping("gateway")
public class GatewayTestPayController {

    @Autowired
    private ITccGatewayRecordService tccGatewayRecordService;

    @Autowired
    DozerBeanMapper mapper;

    @RequestMapping(value = "/recharge", method = RequestMethod.POST)
    public GateWayRes pay(GateWayReq req) {

        TccGatewayRecordVo vo = new TccGatewayRecordVo();
        vo.setPayKey("试试");
        vo.setProductName("测试");
        vo.setOrderNo("122222");
        vo.setOrderPrice("0.01");
        vo.setPayWayCode("");
        vo.setOrderIp("127.0.0.1");
        vo.setOrderDate(new Date());
        vo.setOrderTime(new Date());
        vo.setOrderPeriod(0);
        vo.setReturnUrl("");
        vo.setNotifyUrl("http://www.baidu.com");
        vo.setSign("ssssssss");
        vo.setField1("");
        vo.setField2("");
        vo.setField3("");

        GateWayRes res = new GateWayRes();

        res.setR(tccGatewayRecordService.save(vo));

        return res;
    }
}
