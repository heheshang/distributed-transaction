package com.distributed.transaction.module.gateway.vo;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-20-下午 2:01
 */
@Data
@ToString
public class TccGatewayRecord {

    private String id;

    private String payKey;

    private String productName;

    private String orderNo;

    private String orderPrice;

    private String payWayCode;

    private String orderIp;

    @DateTimeFormat(pattern = "yyyyMMdd")
    private Date orderDate;
    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private Date orderTime;

    private int orderPeriod;

    private String returnUrl;

    private String notifyUrl;

    private String sign;

    /**
     * 扩展字段1
     */
    private String field1;

    /**
     * 扩展字段2
     */
    private String field2;

    /**
     * 扩展字段3
     */
    private String field3;
    private String field4;
    private String field5;
}
