package com.distributed.transaction.module.product.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-26-下午 5:53
 */
@Entity
@Table(name = "tcc_pay_way", schema = "pay_product", catalog = "")
public class TccPayWayEntity implements Serializable {

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

    @Id
    @Column(name = "ID")
    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    @Basic
    @Column(name = "version")
    @Version
    public long getVersion() {

        return version;
    }

    public void setVersion(long version) {

        this.version = version;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {

        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {

        this.createTime = createTime;
    }

    @Basic
    @Column(name = "edit_time")
    public Timestamp getEditTime() {

        return editTime;
    }

    public void setEditTime(Timestamp editTime) {

        this.editTime = editTime;
    }

    @Basic
    @Column(name = "pay_way_code")
    public String getPayWayCode() {

        return payWayCode;
    }

    public void setPayWayCode(String payWayCode) {

        this.payWayCode = payWayCode;
    }

    @Basic
    @Column(name = "pay_way_name")
    public String getPayWayName() {

        return payWayName;
    }

    public void setPayWayName(String payWayName) {

        this.payWayName = payWayName;
    }

    @Basic
    @Column(name = "pay_type_code")
    public String getPayTypeCode() {

        return payTypeCode;
    }

    public void setPayTypeCode(String payTypeCode) {

        this.payTypeCode = payTypeCode;
    }

    @Basic
    @Column(name = "pay_type_name")
    public String getPayTypeName() {

        return payTypeName;
    }

    public void setPayTypeName(String payTypeName) {

        this.payTypeName = payTypeName;
    }

    @Basic
    @Column(name = "pay_product_code")
    public String getPayProductCode() {

        return payProductCode;
    }

    public void setPayProductCode(String payProductCode) {

        this.payProductCode = payProductCode;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }

    @Basic
    @Column(name = "sorts")
    public Integer getSorts() {

        return sorts;
    }

    public void setSorts(Integer sorts) {

        this.sorts = sorts;
    }

    @Basic
    @Column(name = "pay_rate")
    public double getPayRate() {

        return payRate;
    }

    public void setPayRate(double payRate) {

        this.payRate = payRate;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TccPayWayEntity that = (TccPayWayEntity) o;
        return version == that.version &&
                Double.compare(that.payRate, payRate) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(editTime, that.editTime) &&
                Objects.equals(payWayCode, that.payWayCode) &&
                Objects.equals(payWayName, that.payWayName) &&
                Objects.equals(payTypeCode, that.payTypeCode) &&
                Objects.equals(payTypeName, that.payTypeName) &&
                Objects.equals(payProductCode, that.payProductCode) &&
                Objects.equals(status, that.status) &&
                Objects.equals(sorts, that.sorts);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, version, createTime, editTime, payWayCode, payWayName, payTypeCode, payTypeName, payProductCode, status, sorts, payRate);
    }
}
