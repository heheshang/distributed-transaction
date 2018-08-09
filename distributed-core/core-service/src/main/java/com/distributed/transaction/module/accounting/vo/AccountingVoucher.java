package com.distributed.transaction.module.accounting.vo;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 会计凭证
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-06-下午 2:54
 */
@Data
@ToString
public class AccountingVoucher {



    /** 会计分录类型（对应枚举EntryTypeEnum.java） **/
    private Short entryType;

    /** 请求号 (会计系统自动生成) **/
    private String requestNo;

    /** 来源系统 **/
    private Short fromSystem;

    /** 原始凭证号 （交易记录的唯一凭证号） **/
    private String voucherNo;

    /** 会计日期 **/
    private Date accountingDate;

    /** 平台银行帐户变动金额 **/
    private BigDecimal bankChangeAmount = BigDecimal.ZERO;

    /**
     * 付款方账户编号(退款交易：原路退，既交易付款方为payerAccountNo，那么退款也用payerAccountNo作为收钱一方)
     */
    private String payerAccountNo;

    /**
     * 收款方账户编号(退款交易：原路退，既交易付款方为receiverAccountNo，那么退款也用receiverAccountNo作为退钱一方)
     */
    private String receiverAccountNo;

    /** 银行账户 **/
    private String bankAccount;

    /** 银行渠道编号 **/
    private String bankChannelCode;

    /**
     * 付款方帐户变动金额（退款交易：付款方实际变动金额）
     */
    private BigDecimal payerChangeAmount = BigDecimal.ZERO;

    /**
     * 收款方帐户变动金额（退款交易：收款方实际变动金额。）
     */
    private BigDecimal receiverChangeAmount = BigDecimal.ZERO;

    /**
     * 平台利润（退款交易：平台利润实际变动金额。）
     */
    private BigDecimal profit = BigDecimal.ZERO;

    /**
     * 平台收入（退款交易：平台收入实际变动金额）
     */
    private BigDecimal income = BigDecimal.ZERO;

    /**
     * 平台成本（退款交易：平台成本实际变动金额。）
     */
    private BigDecimal cost = BigDecimal.ZERO;

    /** 银行订单号 **/
    private String bankOrderNo;

    /** 付款方账户类型 **/
    private Short payerAccountType;

    /** 支付金额 **/
    private BigDecimal payAmount = BigDecimal.ZERO;

    /** 收款方账户类型 **/
    private Short receiverAccountType;

    /**
     * 收款方手续费(退款交易：原路退，实际退收款方手续费。既:交易收收款方手续费
     * 10元，如分2次退款每次退5元，则receiverFee为5元，如一次退完，则receiverFee为10元)
     */
    private BigDecimal receiverFee = BigDecimal.ZERO;
    /**
     * 付款方手续费（退款交易：原路退，实际退付款方手续费。既:交易收付款方手续费
     * 10元，如分2次退款每次退5元，则payerFee为5元，如一次退完，则payerFee为10元）
     */
    private BigDecimal payerFee = BigDecimal.ZERO;

    /** 分录步骤，1：产生交易 2：清算对账 */
    /**非数据库映射字段，只用于传参 */
    private Short step;

    /**
     * 非数据库映射字段，只用于传参
     */
    private String messageId;

    private String remark;
}
