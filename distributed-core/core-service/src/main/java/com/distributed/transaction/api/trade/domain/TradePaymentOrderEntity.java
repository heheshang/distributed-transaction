package com.distributed.transaction.api.trade.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-25-下午 1:57
 */
@Entity
@Table(name = "trade_payment_order")
@SelectBeforeUpdate
@DynamicInsert
@DynamicUpdate
public class TradePaymentOrderEntity {

    private String id;

    private int version;

    private Timestamp createTime;

    private String editor;

    private String creater;

    private Timestamp editTime;

    private String status;

    private String productName;

    private String merchantOrderNo;

    private BigDecimal orderAmount;

    private String orderFrom;

    private String merchantName;

    private String merchantNo;

    private Timestamp orderTime;

    private Date orderDate;

    private String orderIp;

    private String orderRefererUrl;

    private String returnUrl;

    private String notifyUrl;

    private String cancelReason;

    private Short orderPeriod;

    private Timestamp expireTime;

    private String payWayCode;

    private String payWayName;

    private String remark;

    private String trxType;

    private String payTypeCode;

    private String payTypeName;

    private String fundIntoType;

    private String isRefund;

    private Integer refundTimes;

    private BigDecimal successRefundAmount;

    private String field1;

    private String field2;

    private String field3;

    private String field4;

    private String field5;

    private String trxNo;

    @Id
    @Column(name = "id")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    @Basic
    @Column(name = "version")
    public int getVersion() {

        return version;
    }

    public void setVersion(int version) {

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
    @Column(name = "editor")
    public String getEditor() {

        return editor;
    }

    public void setEditor(String editor) {

        this.editor = editor;
    }

    @Basic
    @Column(name = "creater")
    public String getCreater() {

        return creater;
    }

    public void setCreater(String creater) {

        this.creater = creater;
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
    @Column(name = "status")
    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {

        this.status = status;
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
    @Column(name = "merchant_order_no")
    public String getMerchantOrderNo() {

        return merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {

        this.merchantOrderNo = merchantOrderNo;
    }

    @Basic
    @Column(name = "order_amount")
    public BigDecimal getOrderAmount() {

        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {

        this.orderAmount = orderAmount;
    }

    @Basic
    @Column(name = "order_from")
    public String getOrderFrom() {

        return orderFrom;
    }

    public void setOrderFrom(String orderFrom) {

        this.orderFrom = orderFrom;
    }

    @Basic
    @Column(name = "merchant_name")
    public String getMerchantName() {

        return merchantName;
    }

    public void setMerchantName(String merchantName) {

        this.merchantName = merchantName;
    }

    @Basic
    @Column(name = "merchant_no")
    public String getMerchantNo() {

        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {

        this.merchantNo = merchantNo;
    }

    @Basic
    @Column(name = "order_time")
    public Timestamp getOrderTime() {

        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {

        this.orderTime = orderTime;
    }

    @Basic
    @Column(name = "order_date")
    public Date getOrderDate() {

        return orderDate;
    }

    public void setOrderDate(Date orderDate) {

        this.orderDate = orderDate;
    }

    @Basic
    @Column(name = "order_ip")
    public String getOrderIp() {

        return orderIp;
    }

    public void setOrderIp(String orderIp) {

        this.orderIp = orderIp;
    }

    @Basic
    @Column(name = "order_referer_url")
    public String getOrderRefererUrl() {

        return orderRefererUrl;
    }

    public void setOrderRefererUrl(String orderRefererUrl) {

        this.orderRefererUrl = orderRefererUrl;
    }

    @Basic
    @Column(name = "return_url")
    public String getReturnUrl() {

        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {

        this.returnUrl = returnUrl;
    }

    @Basic
    @Column(name = "notify_url")
    public String getNotifyUrl() {

        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {

        this.notifyUrl = notifyUrl;
    }

    @Basic
    @Column(name = "cancel_reason")
    public String getCancelReason() {

        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {

        this.cancelReason = cancelReason;
    }

    @Basic
    @Column(name = "order_period")
    public Short getOrderPeriod() {

        return orderPeriod;
    }

    public void setOrderPeriod(Short orderPeriod) {

        this.orderPeriod = orderPeriod;
    }

    @Basic
    @Column(name = "expire_time")
    public Timestamp getExpireTime() {

        return expireTime;
    }

    public void setExpireTime(Timestamp expireTime) {

        this.expireTime = expireTime;
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
    @Column(name = "remark")
    public String getRemark() {

        return remark;
    }

    public void setRemark(String remark) {

        this.remark = remark;
    }

    @Basic
    @Column(name = "trx_type")
    public String getTrxType() {

        return trxType;
    }

    public void setTrxType(String trxType) {

        this.trxType = trxType;
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
    @Column(name = "fund_into_type")
    public String getFundIntoType() {

        return fundIntoType;
    }

    public void setFundIntoType(String fundIntoType) {

        this.fundIntoType = fundIntoType;
    }

    @Basic
    @Column(name = "is_refund")
    public String getIsRefund() {

        return isRefund;
    }

    public void setIsRefund(String isRefund) {

        this.isRefund = isRefund;
    }

    @Basic
    @Column(name = "refund_times")
    public Integer getRefundTimes() {

        return refundTimes;
    }

    public void setRefundTimes(Integer refundTimes) {

        this.refundTimes = refundTimes;
    }

    @Basic
    @Column(name = "success_refund_amount")
    public BigDecimal getSuccessRefundAmount() {

        return successRefundAmount;
    }

    public void setSuccessRefundAmount(BigDecimal successRefundAmount) {

        this.successRefundAmount = successRefundAmount;
    }

    @Basic
    @Column(name = "field1")
    public String getField1() {

        return field1;
    }

    public void setField1(String field1) {

        this.field1 = field1;
    }

    @Basic
    @Column(name = "field2")
    public String getField2() {

        return field2;
    }

    public void setField2(String field2) {

        this.field2 = field2;
    }

    @Basic
    @Column(name = "field3")
    public String getField3() {

        return field3;
    }

    public void setField3(String field3) {

        this.field3 = field3;
    }

    @Basic
    @Column(name = "field4")
    public String getField4() {

        return field4;
    }

    public void setField4(String field4) {

        this.field4 = field4;
    }

    @Basic
    @Column(name = "field5")
    public String getField5() {

        return field5;
    }

    public void setField5(String field5) {

        this.field5 = field5;
    }

    @Basic
    @Column(name = "trx_no")
    public String getTrxNo() {

        return trxNo;
    }

    public void setTrxNo(String trxNo) {

        this.trxNo = trxNo;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradePaymentOrderEntity that = (TradePaymentOrderEntity) o;
        return version == that.version &&
                Objects.equals(id, that.id) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(editor, that.editor) &&
                Objects.equals(creater, that.creater) &&
                Objects.equals(editTime, that.editTime) &&
                Objects.equals(status, that.status) &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(merchantOrderNo, that.merchantOrderNo) &&
                Objects.equals(orderAmount, that.orderAmount) &&
                Objects.equals(orderFrom, that.orderFrom) &&
                Objects.equals(merchantName, that.merchantName) &&
                Objects.equals(merchantNo, that.merchantNo) &&
                Objects.equals(orderTime, that.orderTime) &&
                Objects.equals(orderDate, that.orderDate) &&
                Objects.equals(orderIp, that.orderIp) &&
                Objects.equals(orderRefererUrl, that.orderRefererUrl) &&
                Objects.equals(returnUrl, that.returnUrl) &&
                Objects.equals(notifyUrl, that.notifyUrl) &&
                Objects.equals(cancelReason, that.cancelReason) &&
                Objects.equals(orderPeriod, that.orderPeriod) &&
                Objects.equals(expireTime, that.expireTime) &&
                Objects.equals(payWayCode, that.payWayCode) &&
                Objects.equals(payWayName, that.payWayName) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(trxType, that.trxType) &&
                Objects.equals(payTypeCode, that.payTypeCode) &&
                Objects.equals(payTypeName, that.payTypeName) &&
                Objects.equals(fundIntoType, that.fundIntoType) &&
                Objects.equals(isRefund, that.isRefund) &&
                Objects.equals(refundTimes, that.refundTimes) &&
                Objects.equals(successRefundAmount, that.successRefundAmount) &&
                Objects.equals(field1, that.field1) &&
                Objects.equals(field2, that.field2) &&
                Objects.equals(field3, that.field3) &&
                Objects.equals(field4, that.field4) &&
                Objects.equals(field5, that.field5) &&
                Objects.equals(trxNo, that.trxNo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, version, createTime, editor, creater, editTime, status, productName, merchantOrderNo, orderAmount, orderFrom, merchantName, merchantNo, orderTime, orderDate, orderIp, orderRefererUrl, returnUrl, notifyUrl, cancelReason, orderPeriod, expireTime, payWayCode, payWayName, remark, trxType, payTypeCode, payTypeName, fundIntoType, isRefund, refundTimes, successRefundAmount, field1, field2, field3, field4, field5, trxNo);
    }
}
