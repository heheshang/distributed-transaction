package com.distributed.transaction.api.gateway.domain;

import com.distributed.transaction.enums.convert.AreadlyDeadEnumConverter;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 网关流水记录
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-19-下午 5:16
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "tcc_gateway_record")
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

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "order_no", nullable = false)
    private String orderNo;

    @Column(name = "order_price", nullable = false)
    private String orderPrice;

    @Column(name = "pay_way_code", nullable = false)
    @Convert(converter = AreadlyDeadEnumConverter.class)
    private String payWayCode;

    @Column(name = "order_ip")
    private String orderIp;


}
