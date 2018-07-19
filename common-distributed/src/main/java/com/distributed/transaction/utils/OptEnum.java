package com.distributed.transaction.utils;

/**
 * 操作类型
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-04-上午 11:27
 */
public enum OptEnum {
    /**
     * 查询
     */
    QUERY("QUERY","查询"),

    PAGE_QUERY("QUERY","查询"),
    /**
     * 保存
     */
    SAVE("SAVE", "保存"),
    /**
     * 更新
     */
    UPDATE("UPDATE","更新"),
    /**
     * 删除
     */
    DELETE_BY_ID("DELETE_BY_ID", "删除"),
    /**
     * 删除全部
     */
    DELTETE_ALL("DELTETE_ALL", "删除全部"),

    ;

    private String optType;

    private String desc;

    public String getOptType() {

        return this.optType;
    }

    public String getDesc() {

        return this.desc;
    }

    OptEnum(String optType, String desc) {

        this.optType = optType;

        this.desc = desc;

    }
}
