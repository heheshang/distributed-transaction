package com.distributed.transaction.scheduler;

import com.distributed.transaction.service.IBaseService;
import com.distributed.transaction.trade.api.TradeReq;
import com.distributed.transaction.trade.api.TradeRes;
import com.distributed.transaction.trade.api.recharge.RechargeParam;
import com.distributed.transaction.utils.TransTypeEnum;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-04-下午 2:30
 */
@Component
@Log4j2
public class RechargeScheduler {

    @Autowired
    private IBaseService baseService;

    @Scheduled(cron = "0 0/1 4-23 * * ?")
    protected void run() {

        TradeReq tradeReq = new TradeReq();
        tradeReq.setTransTypeEnum(TransTypeEnum.TEST_RECHARGE_PAY);
        RechargeParam param = new RechargeParam();

        param.setTransSeqNo("1233333");

        tradeReq.setParams(param);

        TradeRes res = baseService.process(tradeReq);
        log.info("定时任务返回信息为[{}]", res);
    }
}
