package com.distributed.transaction.controller;

import com.distributed.transaction.enums.trade.TradeRequestTypeEnum;
import com.distributed.transaction.service.IBaseService;
import com.distributed.transaction.trade.api.TradeReq;
import com.distributed.transaction.trade.api.TradeRes;
import com.distributed.transaction.trade.api.banknotify.BankNotifyMessage;
import com.distributed.transaction.trade.api.banknotify.BankNotifyParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-03-下午 2:35
 */
@RestController
@RequestMapping("/bank")
@Log4j2
public class BankMessageApiController {

    @Autowired
    private IBaseService baseService;

    @PostMapping("/message")
    public TradeRes<BankNotifyMessage> handleMessage(@RequestBody TradeReq<BankNotifyParam> tradeReq) {

        log.info("银行异步通知业务处理开始[{}]", tradeReq);

        return baseService.process(TradeRequestTypeEnum.BANK_NOTIFY, tradeReq);
    }
}
