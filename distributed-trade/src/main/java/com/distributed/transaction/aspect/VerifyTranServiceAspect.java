/*
package com.distributed.transaction.aspect;

import com.distributed.transaction.annotations.VerifyUser;
import com.distributed.transaction.interceptor.TradeTransactionInterceptor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

*/
/**
 * 交易记录和交易订单操作切面
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-25-下午 4:30
 *//*

@Component
@Aspect
@Log4j2
public class VerifyTranServiceAspect implements Ordered {


    @Autowired
    private TradeTransactionInterceptor interceptor;

    @Around("target(com.distributed.transaction.checker.impl.*)")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info("切面处理订单及订单记录开始");
        Class<?> className = joinPoint.getTarget().getClass();
        if (className.isAnnotationPresent(VerifyUser.class)) {
            return interceptor.preHandleMethod(joinPoint);
        } else {
            return joinPoint.proceed();
        }

    }

    @Override
    public int getOrder() {

        return 0;
    }
}
*/
