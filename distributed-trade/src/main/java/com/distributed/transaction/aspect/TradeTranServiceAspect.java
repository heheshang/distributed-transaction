package com.distributed.transaction.aspect;

import com.distributed.transaction.interceptor.TradeTransactionInterceptor;
import com.distributed.transaction.annotations.TradeTransType;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * 交易记录和交易订单操作切面
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-25-下午 4:30
 */
@Component
@Aspect
@Log4j2
public class TradeTranServiceAspect implements Ordered {


    @Autowired
    private TradeTransactionInterceptor interceptor;

    @Around("@within(com.distributed.transaction.annotations.TradeTransType)")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info("切面处理订单及订单记录开始");
        Annotation annotation = joinPoint.getClass().getAnnotation(TradeTransType.class);
        if (annotation != null) {
            return interceptor.preHandleMethod(joinPoint);
        } else {
            return joinPoint.proceed();
        }

    }

    @Override
    public int getOrder() {

        return 1;
    }
}
