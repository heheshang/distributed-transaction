package com.distributed.transaction.validation;


import com.distributed.transaction.validation.impl.MoneyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 金额校验的注解
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @date 2018/07/26 下午 5:06
 * @since v1.0
 **/
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MoneyValidator.class)
public @interface Money {

    String message() default "不是有效的金额形式";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
