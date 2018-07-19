package com.distributed.transaction.utils;

/**
 * 交易类型
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-04-上午 11:27
 */
public enum TransEnum {
    /**
     * 充值
     */
    RECHAEGE("recharge", "充值"),
    /**
     * 提现
     */
    WITHDRAW("withdraw", "提现"),
    /**
     * 转账
     */
    TRANSFER("WITHDRAW", "转账"),;

    private String tranCode;

    private String desc;

    public String getTranCode() {

        return this.tranCode;
    }

    public String getDesc() {

        return this.desc;
    }

    TransEnum(String tranCode, String desc) {

        this.tranCode = tranCode;

        this.desc = desc;

    }
}
