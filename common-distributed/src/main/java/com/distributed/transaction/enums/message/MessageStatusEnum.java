
package com.distributed.transaction.enums.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* message状态枚举
* @author ssk www.8win.com Inc.All rights reserved
* @date 2018/08/01 下午 4:16
* @since v1.0
**/
public enum MessageStatusEnum {
	/**
	 *
	 */
	WAITING_CONFIRM("待确认"),

	SENDING("发送中");

	/** 描述 */
	private String desc;

	MessageStatusEnum(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static Map<String, Map<String, Object>> toMap() {
		MessageStatusEnum[] ary = MessageStatusEnum.values();
		Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
		for (int num = 0; num < ary.length; num++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String key = ary[num].name();
			map.put("desc", ary[num].getDesc());
			enumMap.put(key, map);
		}
		return enumMap;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toList() {
		MessageStatusEnum[] ary = MessageStatusEnum.values();
		List list = new ArrayList();
		for (int i = 0; i < ary.length; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("desc", ary[i].getDesc());
			map.put("name", ary[i].name());
			list.add(map);
		}
		return list;
	}

	public static MessageStatusEnum getEnum(String name) {
		MessageStatusEnum[] arry = MessageStatusEnum.values();
		for (int i = 0; i < arry.length; i++) {
			if (arry[i].name().equalsIgnoreCase(name)) {
				return arry[i];
			}
		}
		return null;
	}

	/**
	 * 取枚举的json字符串
	 *
	 * @return
	 */
	public static String getJsonStr() {
		MessageStatusEnum[] enums = MessageStatusEnum.values();
		StringBuffer jsonStr = new StringBuffer("[");
		for (MessageStatusEnum senum : enums) {
			if (!"[".equals(jsonStr.toString())) {
				jsonStr.append(",");
			}
			jsonStr.append("{id:'").append(senum).append("',desc:'").append(senum.getDesc()).append("'}");
		}
		jsonStr.append("]");
		return jsonStr.toString();
	}
}
