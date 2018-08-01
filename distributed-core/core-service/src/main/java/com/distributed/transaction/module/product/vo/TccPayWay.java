package com.distributed.transaction.module.product.vo;

import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-01-上午 10:29
 */
@Data
@ToString
public class TccPayWay {

    private String id;

    private long version;

    private Timestamp createTime;

    private Timestamp editTime;

    private String payWayCode;

    private String payWayName;

    private String payTypeCode;

    private String payTypeName;

    private String payProductCode;

    private String status;

    private Integer sorts;

    private double payRate;
}
