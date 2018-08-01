
package com.distributed.transaction.enums.convert;

import com.distributed.transaction.enums.message.MsgDataTypeEnum;
import com.distributed.transaction.enums.message.NotifyDestinationNameEnum;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 消息消费队列名称枚举
* @author ssk www.8win.com Inc.All rights reserved
* @date 2018/08/01 下午 4:04
* @since v1.0
**/
public class NotifyDestinationNameEnumConverter implements AttributeConverter<NotifyDestinationNameEnum,String> {


	@Override
	public String convertToDatabaseColumn(NotifyDestinationNameEnum attribute) {

		return attribute.name();
	}

	@Override
	public NotifyDestinationNameEnum convertToEntityAttribute(String dbData) {

		return NotifyDestinationNameEnum.getEnum(dbData);
	}
}
