package com.distributed.transaction.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-27-上午 9:40
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({  ElementType.METHOD})
@Documented
public @interface VerifyProduct {

    boolean value() default true;

    String check() default "";
}
