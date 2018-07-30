package com.distributed.transaction.annotations;

import com.distributed.transaction.utils.TradeDataSourceType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-30-下午 3:40
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DataSource {

    TradeDataSourceType value() default TradeDataSourceType.MASTER;
}
