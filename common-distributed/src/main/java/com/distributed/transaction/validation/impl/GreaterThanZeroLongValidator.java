package com.distributed.transaction.validation.impl;


import com.distributed.transaction.validation.GreaterThanZero;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 数字大于0的验证器
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @date 2018/07/26 下午 5:07
 * @since v1.0
 **/
public class GreaterThanZeroLongValidator implements ConstraintValidator<GreaterThanZero, Long> {

    @Override
    public void initialize(GreaterThanZero constraintAnnotation) {

    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {

        return value == null || value > 0;
    }
}
