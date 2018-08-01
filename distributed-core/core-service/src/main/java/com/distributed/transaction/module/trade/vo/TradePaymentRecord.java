package com.distributed.transaction.module.trade.vo;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-30-下午 5:32
 */
@Data
@ToString
public class TradePaymentRecord {

    private String id;


    private Date createTime;

    private String status;

    private String editor;

    private String creater;

    private Date editTime;

    private String productName;

    private String merchantOrderNo;

    /**
     * 交易号
     */
    private String trxNo;

    /**
     * 银行订单号
     */
    private String bankOrderNo;

    private String bankTrxNo;

    private String merchantName;

    private String merchantNo;

    private String payerUserNo;

    private String payerName;

    private BigDecimal payerPayAmount;

    private BigDecimal payerFee;

    private String payerAccountType;

    private String receiverUserNo;

    private String receiverName;

    private BigDecimal receiverPayAmount;

    private BigDecimal receiverFee;

    private String receiverAccountType;

    private String orderIp;

    private String orderRefererUrl;

    private BigDecimal orderAmount;

    private BigDecimal platIncome;

    private BigDecimal feeRate;

    private BigDecimal platCost;

    private BigDecimal platProfit;

    private String returnUrl;

    private String notifyUrl;

    private String payWayCode;

    private String payWayName;

    private Date paySuccessTime;

    private Date completeTime;

    private String isRefund;

    private Integer refundTimes;

    private BigDecimal successRefundAmount;

    private String trxType;

    private String orderFrom;

    private String payTypeCode;

    private String payTypeName;

    private String fundIntoType;

    private String remark;

    private String field1;

    private String field2;

    private String field3;

    private String field4;

    private String field5;

    private String bankReturnMsg;
}
