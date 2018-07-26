package com.distributed.transaction.validation;


import com.distributed.transaction.validation.impl.GreaterThanZeroDoubleValidator;
import com.distributed.transaction.validation.impl.GreaterThanZeroIntegerValidator;
import com.distributed.transaction.validation.impl.GreaterThanZeroLongValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数值大于0的注解
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @date 2018/07/26 下午 5:06
 * @since v1.0
 **/

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {GreaterThanZeroDoubleValidator.class, GreaterThanZeroIntegerValidator.class, GreaterThanZeroLongValidator.class})
public @interface GreaterThanZero {

    String message() default "数值必须大于0";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
