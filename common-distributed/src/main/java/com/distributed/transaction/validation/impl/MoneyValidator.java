package com.distributed.transaction.validation.impl;


import com.distributed.transaction.validation.Money;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 金额的验证器
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @date 2018/07/26 下午 5:07
 * @since v1.0
 **/
public class MoneyValidator implements ConstraintValidator<Money, Double> {

    /**
     * 表示金额的正则表达式
     */
    private static final String moneyReg = "^\\d+(\\.\\d{1,2})?$";

    private static final Pattern moneyPattern = Pattern.compile(moneyReg);

    @Override
    public void initialize(Money constraintAnnotation) {

    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {

        return value == null || moneyPattern.matcher(value.toString()).matches();
    }
}
