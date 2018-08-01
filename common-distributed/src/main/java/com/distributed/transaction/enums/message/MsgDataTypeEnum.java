package com.distributed.transaction.enums.message;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Map;

/**
 * 消息数据类型
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-05-下午 1:12
 */
public enum MsgDataTypeEnum {
    /**
     * xml
     */
    XML("xml", "xml格式"),
    /**
     * json
     */
    JSON("json", "json格式"),;

    private String name;

    private String desc;

    public String getName() {

        return this.name;
    }

    public String getDesc() {

        return this.desc;
    }

    MsgDataTypeEnum(String name, String desc) {

        this.name = name;

        this.desc = desc;

    }


    private static final Object _LOCK = new Object();

    private static Map<String, MsgDataTypeEnum> _NAME_MAP;

    static {

        synchronized (_LOCK) {
            Map<String, MsgDataTypeEnum> nameMap = Maps.newHashMap();

            for (MsgDataTypeEnum type : MsgDataTypeEnum.values()) {

                if (StringUtils.isNotBlank(type.getName())) {

                    nameMap.put(type.getName(), type);

                }

            }

            _NAME_MAP = Collections.unmodifiableMap(nameMap);
        }
    }

    public static MsgDataTypeEnum getByName(String name) {

        try {
            return _NAME_MAP.get(name);
        } catch (Exception e) {
            return null;
        }
    }
}
