package com.distributed.transaction.service.core;

import com.distributed.transaction.BaseMessage;
import com.distributed.transaction.enums.PayTypeEnum;
import com.distributed.transaction.enums.trade.TradeRequestTypeEnum;
import com.distributed.transaction.exception.DistributedException;
import com.distributed.transaction.exception.TradeBizException;
import com.distributed.transaction.register.TranServiceComponentRegister;
import com.distributed.transaction.service.BaseTranService;
import com.distributed.transaction.service.ITranService;
import com.distributed.transaction.trade.api.TradeReq;
import com.distributed.transaction.trade.api.TradeRes;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-26-上午 10:49
 */
@Component
@Log4j2
public class CoreTranServiceImpl extends BaseTranService {

    @Autowired
    TranServiceComponentRegister tranServiceComponentRegister;

    @Override
    protected TradeRes exec(TradeRequestTypeEnum requestTypeEnum, TradeReq tradeReq) {

        try {


            ITranService service = this.buildTranService(requestTypeEnum, tradeReq.getParams().getPayTypeCode());

            if (service == null) {
                log.error("获取交易服务为空");
                throw new TradeBizException(TradeBizException.TRADE_SYSTEM_ERROR, "交易系统异常");
            }

            TradeRes tradeRes = new TradeRes();

            tradeRes.setSuccess(Boolean.TRUE);

            tradeRes.setProcCode("0000");

            tradeRes.setProcDesc("交易处理成功");

            BaseMessage message = service.handle(tradeReq.getParams());

            tradeRes.setMessage(message);

            return tradeRes;
        } catch (Exception e) {

            return handleException(tradeReq, e);

        }

    }

    @Override
    protected TradeRes check(TradeReq tradeReq) throws DistributedException {

        return null;
    }

    @Override
    protected TradeRes handleException(TradeReq tradeReq, Exception e) {

        BaseMessage message = new BaseMessage();

        TradeRes res = new TradeRes();


        if (e instanceof TradeBizException) {

            message.setErrorReason(((TradeBizException) e).getErrMsg());

            message.setErrorCode(((TradeBizException) e).getErrCode());

        } else if (e instanceof DistributedException) {

            message.setErrorReason(((DistributedException) e).getErrMsg());

            message.setErrorCode(((DistributedException) e).getErrCode());
        } else {

            message.setErrorReason("失败");

            message.setErrorCode("4444");
        }

        res.setMessage(message);

        res.setSuccess(Boolean.FALSE);

        log.error("订单[{}],交易类型[{}]交易失败了,异常信息为e=[{}]", tradeReq.getParams().getTransSeqNo(), tradeReq.getParams().getPayTypeCode(), e);

        return res;
    }

    @Override
    protected ITranService buildTranService(TradeRequestTypeEnum requestTypeEnum, String payTypeCode) {

        if (StringUtils.isBlank(payTypeCode)) {

            return null;
        }

        switch (requestTypeEnum) {

            case GATEWAY_TRADE:
                return tranServiceComponentRegister.getGatewayTradeTransService(PayTypeEnum.getEnum(payTypeCode));

            case BANK_NOTIFY:

                return tranServiceComponentRegister.getBankNotifyTransServiceService(PayTypeEnum.getEnum(payTypeCode));
            default:
                return null;

        }

    }
}
