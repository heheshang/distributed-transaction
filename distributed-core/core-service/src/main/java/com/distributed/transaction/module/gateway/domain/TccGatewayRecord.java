package com.distributed.transaction.module.gateway.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * 网关流水记录
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-19-下午 5:16
 */
@Entity
@Table(name = "tcc_gateway_record")
@SelectBeforeUpdate
@DynamicInsert
@DynamicUpdate
@Data
public class TccGatewayRecord {

    /**
     * 主键ID
     */
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "ID")
    private String id;

    @Column(name = "pay_key", nullable = false)
    private String payKey;

    @Column(name = "product_name", nullable = false, length = 50)
    private String productName;

    @Column(name = "order_no", nullable = false, length = 32)
    private String orderNo;

    @Column(name = "order_price", nullable = false)
    private String orderPrice;

    @Column(name = "pay_way_code", nullable = false)
//    @Convert(converter = AreadlyDeadEnumConverter.class)
    private String payWayCode;

    @Column(name = "order_ip")
    private String orderIp;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_date", nullable = false)
    @UpdateTimestamp
    private Date orderDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_time", nullable = false)
    @UpdateTimestamp
    private Date orderTime;

    @Column(name = "order_period")
    private int orderPeriod;

    @Column(name = "return_url")
    private String returnUrl;

    @Column(name = "notify_url", nullable = false, length = 200)
    private String notifyUrl;

    @Column(name = "sign", nullable = false)
    private String sign;

    /**
     * 扩展字段1
     */
    @Column(name = "field1", length = 200)
    private String field1;

    /**
     * 扩展字段2
     */
    @Column(name = "field2", length = 200)
    private String field2;

    /**
     * 扩展字段3
     */
    @Column(name = "field3", length = 200)
    private String field3;

    /**
     * 扩展字段3
     */
    @Column(name = "field4", length = 200)
    private String field4;

    /**
     * 扩展字段3
     */
    @Column(name = "field5", length = 200)
    private String field5;

}
