package com.distributed.transaction.aspect;

import com.distributed.transaction.annotations.TradeTransType;
import com.distributed.transaction.exception.DistributedException;
import com.distributed.transaction.interceptor.TradeTransactionInterceptor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

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
        Class<?> className = joinPoint.getTarget().getClass();
        if (className.isAnnotationPresent(TradeTransType.class)) {
            return interceptor.preHandleMethod(joinPoint);
        } else {
            return joinPoint.proceed();
        }

    }

    @AfterThrowing(value = "@within(com.distributed.transaction.annotations.TradeTransType)", throwing = "e")
    public Object doAfterThrowing(JoinPoint joinPoint, DistributedException e) {

        log.error("异常了{}", e.getMessage(),e.getErrCode());
        return e;
    }


    @Override
    public int getOrder() {

        return 1;
    }
}
