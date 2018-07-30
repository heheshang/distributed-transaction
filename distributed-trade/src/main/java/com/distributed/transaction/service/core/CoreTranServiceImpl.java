package com.distributed.transaction.service.core;

import com.distributed.transaction.exception.DistributedException;
import com.distributed.transaction.register.TranServiceComponentRegister;
import com.distributed.transaction.service.BaseTranService;
import com.distributed.transaction.service.ITranService;
import com.distributed.transaction.BaseMessage;
import com.distributed.transaction.BaseParam;
import com.distributed.transaction.trade.api.TradeReq;
import com.distributed.transaction.trade.api.TradeRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-26-上午 10:49
 */
@Component
public class CoreTranServiceImpl extends BaseTranService {

    @Autowired

    TranServiceComponentRegister tranServiceComponentRegister;

    @Override
    protected TradeRes exec(TradeReq tradeReq) {

        ITranService service = tranServiceComponentRegister.getTransMessage(tradeReq.getTransTypeEnum());
        TradeRes tradeRes = new TradeRes();
        try {
            BaseMessage message = service.handle((BaseParam) tradeReq.getParams());

            tradeRes.setMessage(message);

        } catch (Exception e) {

           return handleException(tradeReq, e);

        }
        return tradeRes;
    }

    @Override
    protected TradeRes check(TradeReq tradeReq) throws DistributedException {

        return null;
    }

    @Override
    protected TradeRes handleException(TradeReq tradeReq, Exception e) {

        BaseMessage message = new BaseMessage();
        TradeRes res = new TradeRes();


        if (e instanceof DistributedException) {
            message.setErrorReason(((DistributedException) e).getErrMsg());
            message.setErrorCode(((DistributedException) e).getErrCode());
        } else {
            message.setErrorReason("失败");
            message.setErrorCode("4444");
        }
        res.setMessage(message);
        res.setSuccess(Boolean.FALSE);
        return res;
    }
}
