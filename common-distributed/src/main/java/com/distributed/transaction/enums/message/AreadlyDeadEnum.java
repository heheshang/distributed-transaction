package com.distributed.transaction.enums.message;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Map;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-05-下午 2:13
 */
public enum AreadlyDeadEnum {
    /**
     * 是否死亡
     */
    YES("YES","是"),
    /**
     * 否
     */
    NO("NO","否"),

    ;

    private String name;

    private String desc;

    public String getName() {

        return this.name;
    }

    public String getDesc() {

        return this.desc;
    }

    AreadlyDeadEnum(String name, String desc) {

        this.name = name;

        this.desc = desc;

    }
    private static final Object _LOCK = new Object();
    private static Map<String, AreadlyDeadEnum> _NAME_MAP;
    static {

        synchronized (_LOCK) {
            Map<String, AreadlyDeadEnum> nameMap = Maps.newHashMap();

            for (AreadlyDeadEnum type : AreadlyDeadEnum.values()) {

                if (StringUtils.isNotBlank(type.getName())) {

                    nameMap.put(type.getName(), type);

                }

            }

            _NAME_MAP = Collections.unmodifiableMap(nameMap);
        }
    }

   public static AreadlyDeadEnum getByName(String name){
       try {
           return _NAME_MAP.get(name);
       } catch (Exception e) {
           return null;
       }
   }
}
