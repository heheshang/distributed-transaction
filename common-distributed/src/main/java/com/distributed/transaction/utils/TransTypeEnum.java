package com.distributed.transaction.utils;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Map;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-25-下午 3:53
 */
public enum TransTypeEnum {
    /**
     * 模拟支付测试
     */
    TEST_RECHARGE_PAY("TEST_RECHARGE_PAY"),
    WECHAT_RECHARGE_PAY("WECHAT_RECHARGE_PAY"),
    ALI_RECHARGE_PAY("ALI_RECHARGE_PAY"),;


    public final String value;

    TransTypeEnum(String value) {

        this.value = value;
    }





    private static final Object _LOCK = new Object();
    private static Map<String, TransTypeEnum> _VALUE_MAP;
    static {

        synchronized (_LOCK) {
            Map<String, TransTypeEnum> nameMap = Maps.newHashMap();

            for (TransTypeEnum type : TransTypeEnum.values()) {

                if (StringUtils.isNotBlank(type.value)) {

                    nameMap.put(type.value, type);

                }

            }

            _VALUE_MAP = Collections.unmodifiableMap(nameMap);
        }
    }

    public static TransTypeEnum getByValue(String name){
        try {
            return _VALUE_MAP.get(name);
        } catch (Exception e) {
            return null;
        }
    }
}
