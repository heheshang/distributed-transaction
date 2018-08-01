package com.distributed.transaction.annotations;

import com.distributed.transaction.enums.PayTypeEnum;
import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 交易服务注解
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-25-下午 3:53
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
@Component
public @interface TradeTransType {

    PayTypeEnum value() default PayTypeEnum.TEST_PAY_HTTP_CLIENT;
}
