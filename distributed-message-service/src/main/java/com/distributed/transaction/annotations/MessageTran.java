package com.distributed.transaction.annotations;

import com.distributed.transaction.utils.OptEnum;
import com.distributed.transaction.utils.TransEnum;
import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * cglib 动态代理导致注解丢失问题及如何修改 注解允许被继承
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-05-下午 2:51
 * 设置proxy-target-class为true即使用cglib的方式代理对象，默认是jdk方式代理。
 * <p>
 * jdk的动态代理不支持类注入，只支持接口方式注入。
 * 在自定义注解上添加{@link Inherited}。如果是第三方的注解，
 * 调整项目接口层或者拿到这个注解通过代码方式加上{@link Inherited}注解,
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
@Component
public @interface MessageTran {

    TransEnum tran() default TransEnum.RECHAEGE;

    OptEnum opt() default OptEnum.QUERY;
}
