package com.distributed.transaction.module.user;

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
@Table(name = "tcc_user_pay_config", schema = "pay_product", catalog = "")
public class TccUserPayConfigEntity {

    private String id;

    private Timestamp createTime;

    private Timestamp editTime;

    private long version;

    private String remark;

    private String status;

    private String auditStatus;

    private String isAutoSett;

    private String productCode;

    private String productName;

    private String userNo;

    private String userName;

    private Integer riskDay;

    private String payKey;

    private String fundIntoType;

    private String paySecret;

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
    @Column(name = "remark")
    public String getRemark() {

        return remark;
    }

    public void setRemark(String remark) {

        this.remark = remark;
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
    @Column(name = "audit_status")
    public String getAuditStatus() {

        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {

        this.auditStatus = auditStatus;
    }

    @Basic
    @Column(name = "is_auto_sett")
    public String getIsAutoSett() {

        return isAutoSett;
    }

    public void setIsAutoSett(String isAutoSett) {

        this.isAutoSett = isAutoSett;
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
    @Column(name = "user_no")
    public String getUserNo() {

        return userNo;
    }

    public void setUserNo(String userNo) {

        this.userNo = userNo;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

    @Basic
    @Column(name = "risk_day")
    public Integer getRiskDay() {

        return riskDay;
    }

    public void setRiskDay(Integer riskDay) {

        this.riskDay = riskDay;
    }

    @Basic
    @Column(name = "pay_key")
    public String getPayKey() {

        return payKey;
    }

    public void setPayKey(String payKey) {

        this.payKey = payKey;
    }

    @Basic
    @Column(name = "fund_into_type")
    public String getFundIntoType() {

        return fundIntoType;
    }

    public void setFundIntoType(String fundIntoType) {

        this.fundIntoType = fundIntoType;
    }

    @Basic
    @Column(name = "pay_secret")
    public String getPaySecret() {

        return paySecret;
    }

    public void setPaySecret(String paySecret) {

        this.paySecret = paySecret;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TccUserPayConfigEntity that = (TccUserPayConfigEntity) o;
        return version == that.version &&
                Objects.equals(id, that.id) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(editTime, that.editTime) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(status, that.status) &&
                Objects.equals(auditStatus, that.auditStatus) &&
                Objects.equals(isAutoSett, that.isAutoSett) &&
                Objects.equals(productCode, that.productCode) &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(userNo, that.userNo) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(riskDay, that.riskDay) &&
                Objects.equals(payKey, that.payKey) &&
                Objects.equals(fundIntoType, that.fundIntoType) &&
                Objects.equals(paySecret, that.paySecret);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, createTime, editTime, version, remark, status, auditStatus, isAutoSett, productCode, productName, userNo, userName, riskDay, payKey, fundIntoType, paySecret);
    }
}
