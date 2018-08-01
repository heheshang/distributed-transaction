package com.distributed.transaction.annotations;

import com.distributed.transaction.enums.verify.UserVerifyEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用户校验注解开启默认进行校验
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-27-上午 9:32
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Documented
public @interface VerifyUser {

    boolean value() default true;

    /**
     * 配置校验器命令
     * @return
     */
    UserVerifyEnum[] check() ;
}
