package com.distributed.transaction.enums.convert;

import com.distributed.transaction.enums.message.MessageMqQueueEnum;

import javax.persistence.AttributeConverter;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-05-下午 1:22
 */
public class ConsumerQueueEnumConverter implements AttributeConverter<MessageMqQueueEnum, String> {


    @Override
    public String convertToDatabaseColumn(MessageMqQueueEnum attribute) {

        return attribute.getName();
    }

    @Override
    public MessageMqQueueEnum convertToEntityAttribute(String dbData) {

        return MessageMqQueueEnum.getByName(dbData);
    }
}
