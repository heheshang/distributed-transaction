package com.distributed.transaction.module.user.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-26-下午 5:53
 */
@Entity
@Table(name = "tcc_user_pay_info", schema = "pay_product", catalog = "")
@DynamicUpdate
@DynamicInsert
public class TccUserPayInfoEntity implements Serializable {

    private String id;

    private Timestamp createTime;

    private Timestamp editTime;

    private long version;

    private String remark;

    private String status;

    private String appId;

    private String appSectet;

    private String merchantId;

    private String appType;

    private String userNo;

    private String userName;

    private String partnerKey;

    private String payWayCode;

    private String payWayName;

    @Id
    @Column(name = "id_")
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
    @Column(name = "app_id")
    public String getAppId() {

        return appId;
    }

    public void setAppId(String appId) {

        this.appId = appId;
    }

    @Basic
    @Column(name = "app_sectet")
    public String getAppSectet() {

        return appSectet;
    }

    public void setAppSectet(String appSectet) {

        this.appSectet = appSectet;
    }

    @Basic
    @Column(name = "merchant_id")
    public String getMerchantId() {

        return merchantId;
    }

    public void setMerchantId(String merchantId) {

        this.merchantId = merchantId;
    }

    @Basic
    @Column(name = "app_type")
    public String getAppType() {

        return appType;
    }

    public void setAppType(String appType) {

        this.appType = appType;
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
    @Column(name = "partner_key")
    public String getPartnerKey() {

        return partnerKey;
    }

    public void setPartnerKey(String partnerKey) {

        this.partnerKey = partnerKey;
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

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TccUserPayInfoEntity that = (TccUserPayInfoEntity) o;
        return version == that.version &&
                Objects.equals(id, that.id) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(editTime, that.editTime) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(status, that.status) &&
                Objects.equals(appId, that.appId) &&
                Objects.equals(appSectet, that.appSectet) &&
                Objects.equals(merchantId, that.merchantId) &&
                Objects.equals(appType, that.appType) &&
                Objects.equals(userNo, that.userNo) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(partnerKey, that.partnerKey) &&
                Objects.equals(payWayCode, that.payWayCode) &&
                Objects.equals(payWayName, that.payWayName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, createTime, editTime, version, remark, status, appId, appSectet, merchantId, appType, userNo, userName, partnerKey, payWayCode, payWayName);
    }
}
