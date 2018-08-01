package com.distributed.transaction.scheduler;

import com.distributed.transaction.service.IBaseService;
import com.distributed.transaction.trade.api.TradeReq;
import com.distributed.transaction.trade.api.TradeRes;
import com.distributed.transaction.trade.api.recharge.RechargeParam;
import com.distributed.transaction.enums.PayTypeEnum;
import com.distributed.transaction.enums.PayWayEnum;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

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

    @Scheduled(cron = "0 0/1 * * * ? ")
    protected void run() {

        TradeReq tradeReq = new TradeReq();
        RechargeParam param = new RechargeParam();
        param.setProductName("测试模拟httpclient支付");
        param.setMerchantOrderNo(String.valueOf(System.currentTimeMillis()));
        param.setOrderAmount(new BigDecimal("1000"));
        param.setOrderFrom("");
        param.setMerchantName("");
        param.setMerchantNo("");
        param.setOrderTime(new Date());
        param.setOrderDate(new Date());
        param.setOrderIp("127.0.0.1");
        param.setOrderRefererUrl("");
        param.setReturnUrl("");
        param.setNotifyUrl("");
        param.setCancelReason("");
        param.setOrderPeriod(0);
        param.setExpireTime(new Date());
        param.setPayWayCode("");
        param.setPayWayName("");
        param.setRemark("");
        param.setTrxType("");

        param.setFundIntoType("");
        param.setIsRefund("");
        param.setRefundTimes((short) 0);
        param.setSuccessRefundAmount(new BigDecimal("0"));
        param.setField1("");
        param.setField2("");
        param.setField3("");
        param.setField4("");
        param.setField5("");
        param.setTxnTm(new Date());

        param.setPayWayCode(PayWayEnum.TEST_PAY_HTTP_CLIENT.name());
        param.setPayTypeCode(PayTypeEnum.TEST_PAY_HTTP_CLIENT.getWay());
        param.setTransSeqNo("1233333");
        param.setPayKey("8ba459f55ff04f39b0128a3cb4a48f2b");

        tradeReq.setParams(param);

        TradeRes res = baseService.process(tradeReq);
        log.info("定时任务返回信息为[{}]", res);
    }
}
