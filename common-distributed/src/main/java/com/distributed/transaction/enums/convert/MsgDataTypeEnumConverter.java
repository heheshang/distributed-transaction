package com.distributed.transaction.enums.convert;

import com.distributed.transaction.utils.MsgDataTypeEnum;

import javax.persistence.AttributeConverter;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-05-下午 1:22
 */
public class MsgDataTypeEnumConverter implements AttributeConverter<MsgDataTypeEnum,String> {


    @Override
    public String convertToDatabaseColumn(MsgDataTypeEnum attribute) {

        return attribute.getName();
    }

    @Override
    public MsgDataTypeEnum convertToEntityAttribute(String dbData) {

        return MsgDataTypeEnum.getByName(dbData);
    }
}
