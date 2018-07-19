package com.distributed.transaction.annotations;

import com.distributed.transaction.DatabaseType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
*
* @author ssk www.8win.com Inc.All rights reserved
* @date 2018/07/18 下午 5:43
* @since v1.0
**/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DS {
    DatabaseType value() default DatabaseType.MASTER;
}