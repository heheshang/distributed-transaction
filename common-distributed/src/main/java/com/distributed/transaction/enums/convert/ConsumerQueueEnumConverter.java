package com.distributed.transaction.enums.convert;

import com.distributed.transaction.utils.ConsumerQueueEnum;

import javax.persistence.AttributeConverter;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-05-下午 1:22
 */
public class ConsumerQueueEnumConverter implements AttributeConverter<ConsumerQueueEnum, String> {


    @Override
    public String convertToDatabaseColumn(ConsumerQueueEnum attribute) {

        return attribute.getName();
    }

    @Override
    public ConsumerQueueEnum convertToEntityAttribute(String dbData) {

        return ConsumerQueueEnum.getByName(dbData);
    }
}
