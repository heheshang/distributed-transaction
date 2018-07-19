package com.distributed.transaction.annotations;

import com.distributed.transaction.utils.TransEnum;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 交易类型注解
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-04-上午 11:31
 */
@Component
public @interface TransType {

    TransEnum type() default TransEnum.RECHAEGE;

}
