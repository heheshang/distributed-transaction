package com.distributed.transaction.interceptor;

import com.distributed.transaction.api.trade.repository.TradePaymentOrderRepository;
import com.distributed.transaction.api.trade.repository.TradePaymentRecordRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.builder.ToStringBuilder;
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
@Log4j2
public class TradeTransactionInterceptor {

    @Autowired
    private TradePaymentOrderRepository tradePaymentOrderRepository;

    @Autowired
    private TradePaymentRecordRepository tradePaymentRecordRepository;


    public Object preHandleMethod(ProceedingJoinPoint pjp) throws Throwable {

        Object[] arg = pjp.getArgs();

        log.info("切面处理订单记录开始,处理请求为:[{}]", ToStringBuilder.reflectionToString(arg));

        

        return pjp.proceed();
    }
}
