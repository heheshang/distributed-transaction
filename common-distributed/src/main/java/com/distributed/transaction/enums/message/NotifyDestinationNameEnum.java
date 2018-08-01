
package com.distributed.transaction.enums.message;

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
public enum NotifyDestinationNameEnum {

	/**
	 * 会计队列
	 */
	ACCOUNTING_NOTIFY("会计队列"),

	/**
	 * 银行队列
	 */
	BANK_NOTIFY("银行队列")	,

	/**
	 * 商户通知
	 */
	MERCHANT_NOTIFY("商户通知队列");

	/** 描述 */
	private String desc;

	private NotifyDestinationNameEnum(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static NotifyDestinationNameEnum getEnum(String enumName) {
		NotifyDestinationNameEnum resultEnum = null;
		NotifyDestinationNameEnum[] enumAry = NotifyDestinationNameEnum.values();
		for (int i = 0; i < enumAry.length; i++) {
			if (enumAry[i].name().equals(enumName)) {
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}

	public static Map<String, Map<String, Object>> toMap() {
		NotifyDestinationNameEnum[] ary = NotifyDestinationNameEnum.values();
		Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
		for (int num = 0; num < ary.length; num++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String key = ary[num].name();
			map.put("desc", ary[num].getDesc());
			map.put("name", ary[num].name());
			enumMap.put(key, map);
		}
		return enumMap;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toList() {
		NotifyDestinationNameEnum[] ary = NotifyDestinationNameEnum.values();
		List list = new ArrayList();
		for (int i = 0; i < ary.length; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("desc", ary[i].getDesc());
			map.put("name", ary[i].name());
			list.add(map);
		}
		return list;
	}

}
