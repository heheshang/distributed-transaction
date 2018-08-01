package com.distributed.transaction.enums.convert;

import com.distributed.transaction.enums.message.MessageStatusEnum;

import javax.persistence.AttributeConverter;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-01-下午 4:15
 */
public class MessageStatusEnumConverter implements AttributeConverter<MessageStatusEnum, String> {

    @Override
    public String convertToDatabaseColumn(MessageStatusEnum attribute) {

        return attribute.name();
    }

    @Override
    public MessageStatusEnum convertToEntityAttribute(String dbData) {

        return MessageStatusEnum.getEnum(dbData);
    }
}
