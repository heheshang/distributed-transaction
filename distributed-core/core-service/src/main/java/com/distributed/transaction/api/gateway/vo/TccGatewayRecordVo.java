package com.distributed.transaction.api.gateway.vo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-20-下午 2:01
 */
@Data
@ToString
public class TccGatewayRecordVo  {

    private String id;

    private String payKey;

    private String productName;

    private String orderNo;

    private String orderPrice;

    private String payWayCode;

    private String orderIp;

    private Date orderDate;

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
}
