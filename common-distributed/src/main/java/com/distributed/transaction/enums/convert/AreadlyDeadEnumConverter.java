package com.distributed.transaction.enums.convert;

import com.distributed.transaction.utils.AreadlyDeadEnum;

import javax.persistence.AttributeConverter;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-05-下午 1:22
 */
public class AreadlyDeadEnumConverter implements AttributeConverter<AreadlyDeadEnum,String> {


    @Override
    public String convertToDatabaseColumn(AreadlyDeadEnum attribute) {

        return attribute.getName();
    }

    @Override
    public AreadlyDeadEnum convertToEntityAttribute(String dbData) {

        return AreadlyDeadEnum.getByName(dbData);
    }
}
