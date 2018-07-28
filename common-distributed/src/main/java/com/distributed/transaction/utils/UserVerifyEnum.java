package com.distributed.transaction.utils;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Map;

/**
 * 用户校验枚举
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-27-下午 6:03
 */

public enum UserVerifyEnum {
    /**
     * 实名认证
     */
    USER_AUTH("USER_AUTH", "实名认证"),
    /**
     * 邮箱认证
     */
    USER_MAIL("USER_MAIL", "邮箱认证"),;


    private String authCode;
    private String desc;

    public String getAuthCode() {

        return this.authCode;
    }

    public String getDesc() {

        return this.desc;
    }

    UserVerifyEnum(String authCode, String desc) {

        this.authCode = authCode;

        this.desc = desc;

    }

    private static final Object _LOCK = new Object();
    private static Map<String, UserVerifyEnum> _NAME_MAP;

    static {

        synchronized (_LOCK) {
            Map<String, UserVerifyEnum> nameMap = Maps.newHashMap();

            for (UserVerifyEnum type : UserVerifyEnum.values()) {

                if (StringUtils.isNotBlank(type.getAuthCode())) {

                    nameMap.put(type.getAuthCode(), type);

                }

            }

            _NAME_MAP = Collections.unmodifiableMap(nameMap);
        }
    }

    public static UserVerifyEnum getByName(String name) {
        try {
            return _NAME_MAP.get(name);
        } catch (Exception e) {
            return null;
        }
    }
}
