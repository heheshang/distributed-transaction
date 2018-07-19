package com.distributed.transaction.api.gateway.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

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

}
