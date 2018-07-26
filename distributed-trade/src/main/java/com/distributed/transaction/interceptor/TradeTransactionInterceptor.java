package com.distributed.transaction.interceptor;

import com.distributed.transaction.api.trade.repository.TradePaymentOrderRepository;
import com.distributed.transaction.api.trade.repository.TradePaymentRecordRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 交易记录拦截器
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-26-下午 4:20
 */
@Component
public class TradeTransactionInterceptor {

    @Autowired
    private TradePaymentOrderRepository tradePaymentOrderRepository;

    @Autowired
    private TradePaymentRecordRepository tradePaymentRecordRepository;



    public Object preHandleMethod(ProceedingJoinPoint pjp) throws Throwable {

        return pjp.proceed();
    }
}
