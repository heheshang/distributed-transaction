package com.distributed.transaction.controller;

import com.distributed.transaction.register.TranServiceComponentRegister;
import com.distributed.transaction.register.TransTypeEnum;
import com.distributed.transaction.service.ITranService;
import com.distributed.transaction.trade.api.BaseMessage;
import com.distributed.transaction.trade.api.TradeReq;
import com.distributed.transaction.trade.api.TradeRes;
import com.distributed.transaction.trade.api.recharge.RechargeMessage;
import com.distributed.transaction.trade.api.recharge.RechargeParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 交易控制器
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-24-下午 4:24
 */
@RestController
@RequestMapping("/trade")
@Log4j2
public class TradeApiController {


    @PostMapping("/recharge")
    public TradeRes<RechargeMessage> recharge(@RequestBody TradeReq<RechargeParam> req) {

        log.info("交易业务处理开始[{}]",req);

        TradeRes res = new TradeRes();
        RechargeMessage message = new RechargeMessage();
        message.setSuccess(true);
        res.setR(message);
        ITranService service = TranServiceComponentRegister.getTransMessage(TransTypeEnum.TEST_RECHARGE_PAY);
        if (service==null){
            log.error("没有找到有效的服务");
        }

        service.process();

        return res;
    }
}
