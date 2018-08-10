package com.distributed.transaction.utils.dozer;

import com.distributed.transaction.utils.DateUtils;
import org.dozer.CustomConverter;

import java.time.LocalDateTime;
import java.util.Date;

/**
*
* @author ssk www.8win.com Inc.All rights reserved
* @date 2018/08/10 上午 11:47
* @since v1.0
**/
public class LegacyDateToJdk8DateConverter implements CustomConverter {

    @Override
    public Object convert(Object destination, Object source, Class<?> destinationClass, Class<?> sourceClass) {

        if (destinationClass == null || sourceClass == null) {
            return destination;
        }

        if (source == null) {
            destination = null;
        }
        else if (destinationClass.isAssignableFrom(LocalDateTime.class) && sourceClass.isAssignableFrom(Date.class)) {
            destination = DateUtils.convertFrom((Date) source);
        }
        else if (destinationClass.isAssignableFrom(Date.class) && sourceClass.isAssignableFrom(LocalDateTime.class)) {
            destination = DateUtils.convertTo((LocalDateTime) source);
        }

        return destination;
    }
}
