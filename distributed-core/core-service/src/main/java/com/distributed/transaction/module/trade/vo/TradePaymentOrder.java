package com.distributed.transaction.module.trade.vo;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-30-下午 5:30
 */
@Data
@ToString
public class TradePaymentOrder {

    private String id;


    private Date createTime;

    private String editor;

    private String creater;

    private Date editTime;

    /**
     * 交易状态
     */
    private String status;

    private String productName;

    private String merchantOrderNo;

    private BigDecimal orderAmount;

    private String orderFrom;

    private String merchantName;

    private String merchantNo;

    private Date orderTime;

    private Date orderDate;

    private String orderIp;

    private String orderRefererUrl;

    private String returnUrl;

    private String notifyUrl;

    private String cancelReason;

    private Short orderPeriod;

    private Date expireTime;

    private String payWayCode;

    private String payWayName;

    private String remark;

    private String trxType;

    private String payTypeCode;

    private String payTypeName;

    private String fundIntoType;

    private String isRefund;

    private Integer refundTimes;

    private BigDecimal successRefundAmount;

    private String field1;

    private String field2;

    private String field3;

    private String field4;

    private String field5;

    private String trxNo;
}
