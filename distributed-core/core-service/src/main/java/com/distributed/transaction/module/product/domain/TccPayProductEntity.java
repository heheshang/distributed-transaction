package com.distributed.transaction.module.product.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-26-下午 5:53
 */
@Entity
@Table(name = "tcc_pay_product", schema = "pay_product", catalog = "")
@DynamicUpdate
@DynamicInsert
public class TccPayProductEntity {

    private String id;

    private Timestamp createTime;

    private Timestamp editTime;

    private long version;

    private String status;

    private String productCode;

    private String productName;

    private String auditStatus;

    @Id
    @Column(name = "id")
    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
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
    @Column(name = "version")
    public long getVersion() {

        return version;
    }

    public void setVersion(long version) {

        this.version = version;
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
    @Column(name = "product_code")
    public String getProductCode() {

        return productCode;
    }

    public void setProductCode(String productCode) {

        this.productCode = productCode;
    }

    @Basic
    @Column(name = "product_name")
    public String getProductName() {

        return productName;
    }

    public void setProductName(String productName) {

        this.productName = productName;
    }

    @Basic
    @Column(name = "audit_status")
    public String getAuditStatus() {

        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {

        this.auditStatus = auditStatus;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TccPayProductEntity that = (TccPayProductEntity) o;
        return version == that.version &&
                Objects.equals(id, that.id) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(editTime, that.editTime) &&
                Objects.equals(status, that.status) &&
                Objects.equals(productCode, that.productCode) &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(auditStatus, that.auditStatus);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, createTime, editTime, version, status, productCode, productName, auditStatus);
    }
}
