package com.distributed.transaction.service;

import com.distributed.transaction.register.TranServiceComponentRegister;
import com.distributed.transaction.register.TransTypeEnum;
import com.distributed.transaction.trade.api.TradeReq;
import com.distributed.transaction.trade.api.TradeRes;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-04-下午 2:05
 */
@Log4j2
@Component
public abstract class BaseTranService implements IBaseService<TradeReq,TradeRes> {

    @Override
    public TradeRes process(TradeReq tradeReq) {

        TradeRes res = new TradeRes();

        ITranService service = TranServiceComponentRegister.getTransMessage(TransTypeEnum.TEST_RECHARGE_PAY);
        if (service == null) {
            log.error("没有找到有效的服务");
        }
        service.handle(tradeReq.getT());
        return  null;
    }

    public abstract void check(TradeReq tradeReq);

    public abstract TradeRes handleException(TradeReq tradeReq);
}
