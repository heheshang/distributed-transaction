package com.distributed.transaction.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.ComponentScan;
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


    @Around("@within(com.distributed.transaction.register.TransType)")
    public void doAroundAdvice(ProceedingJoinPoint joinPoint) {

        log.info("切面处理订单及订单记录开始");
        return;
    }

    @Override
    public int getOrder() {

        return 1;
    }
}
