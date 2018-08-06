package com.distributed.transaction.module.trade.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-25-下午 1:57
 */
@Entity
@Table(name = "trade_payment_record")
@SelectBeforeUpdate
@DynamicInsert
@DynamicUpdate
public class TradePaymentRecordEntity implements Serializable {

    private String id;

    private int version;

    private Date createTime;

    private String status;

    private String editor;

    private String creater;

    private Date editTime;

    private String productName;

    private String merchantOrderNo;

    private String trxNo;

    private String bankOrderNo;

    private String bankTrxNo;

    private String merchantName;

    private String merchantNo;

    private String payerUserNo;

    private String payerName;

    private BigDecimal payerPayAmount;

    private BigDecimal payerFee;

    private String payerAccountType;

    private String receiverUserNo;

    private String receiverName;

    private BigDecimal receiverPayAmount;

    private BigDecimal receiverFee;

    private String receiverAccountType;

    private String orderIp;

    private String orderRefererUrl;

    private BigDecimal orderAmount;

    private BigDecimal platIncome;

    private BigDecimal feeRate;

    private BigDecimal platCost;

    private BigDecimal platProfit;

    private String returnUrl;

    private String notifyUrl;

    private String payWayCode;

    private String payWayName;

    private Date paySuccessTime;

    private Date completeTime;

    private String isRefund;

    private Integer refundTimes;

    private BigDecimal successRefundAmount;

    private String trxType;

    private String orderFrom;

    private String payTypeCode;

    private String payTypeName;

    private String fundIntoType;

    private String remark;

    private String field1;

    private String field2;

    private String field3;

    private String field4;

    private String field5;

    private String bankReturnMsg;

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
    @Version
    public int getVersion() {

        return version;
    }

    public void setVersion(int version) {

        this.version = version;
    }

    @Basic
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    public Date getCreateTime() {

        return createTime;
    }

    public void setCreateTime(Date createTime) {

        this.createTime = createTime == null ? new Date() : createTime;
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
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    public Date getEditTime() {

        return editTime;
    }

    public void setEditTime(Date editTime) {

        this.editTime = null == editTime ? new Date() : editTime;
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
    @Column(name = "trx_no")
    public String getTrxNo() {

        return trxNo;
    }

    public void setTrxNo(String trxNo) {

        this.trxNo = trxNo;
    }

    @Basic
    @Column(name = "bank_order_no")
    public String getBankOrderNo() {

        return bankOrderNo;
    }

    public void setBankOrderNo(String bankOrderNo) {

        this.bankOrderNo = bankOrderNo;
    }

    @Basic
    @Column(name = "bank_trx_no")
    public String getBankTrxNo() {

        return bankTrxNo;
    }

    public void setBankTrxNo(String bankTrxNo) {

        this.bankTrxNo = bankTrxNo;
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
    @Column(name = "payer_user_no")
    public String getPayerUserNo() {

        return payerUserNo;
    }

    public void setPayerUserNo(String payerUserNo) {

        this.payerUserNo = payerUserNo;
    }

    @Basic
    @Column(name = "payer_name")
    public String getPayerName() {

        return payerName;
    }

    public void setPayerName(String payerName) {

        this.payerName = payerName;
    }

    @Basic
    @Column(name = "payer_pay_amount", scale = 2)
    public BigDecimal getPayerPayAmount() {

        return payerPayAmount;
    }

    public void setPayerPayAmount(BigDecimal payerPayAmount) {

        this.payerPayAmount = payerPayAmount;
    }

    @Basic
    @Column(name = "payer_fee")
    public BigDecimal getPayerFee() {

        return payerFee;
    }

    public void setPayerFee(BigDecimal payerFee) {

        this.payerFee = payerFee;
    }

    @Basic
    @Column(name = "payer_account_type")
    public String getPayerAccountType() {

        return payerAccountType;
    }

    public void setPayerAccountType(String payerAccountType) {

        this.payerAccountType = payerAccountType;
    }

    @Basic
    @Column(name = "receiver_user_no")
    public String getReceiverUserNo() {

        return receiverUserNo;
    }

    public void setReceiverUserNo(String receiverUserNo) {

        this.receiverUserNo = receiverUserNo;
    }

    @Basic
    @Column(name = "receiver_name")
    public String getReceiverName() {

        return receiverName;
    }

    public void setReceiverName(String receiverName) {

        this.receiverName = receiverName;
    }

    @Basic
    @Column(name = "receiver_pay_amount", scale = 2)
    public BigDecimal getReceiverPayAmount() {

        return receiverPayAmount;
    }

    public void setReceiverPayAmount(BigDecimal receiverPayAmount) {

        this.receiverPayAmount = receiverPayAmount;
    }

    @Basic
    @Column(name = "receiver_fee")
    public BigDecimal getReceiverFee() {

        return receiverFee;
    }

    public void setReceiverFee(BigDecimal receiverFee) {

        this.receiverFee = receiverFee;
    }

    @Basic
    @Column(name = "receiver_account_type")
    public String getReceiverAccountType() {

        return receiverAccountType;
    }

    public void setReceiverAccountType(String receiverAccountType) {

        this.receiverAccountType = receiverAccountType;
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
    @Column(name = "order_amount", scale = 2)
    public BigDecimal getOrderAmount() {

        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {

        this.orderAmount = orderAmount;
    }

    @Basic
    @Column(name = "plat_income", scale = 2)
    public BigDecimal getPlatIncome() {

        return platIncome;
    }

    public void setPlatIncome(BigDecimal platIncome) {

        this.platIncome = platIncome;
    }

    @Basic
    @Column(name = "fee_rate", scale = 3)
    public BigDecimal getFeeRate() {

        return feeRate;
    }

    public void setFeeRate(BigDecimal feeRate) {

        this.feeRate = feeRate;
    }

    @Basic
    @Column(name = "plat_cost")
    public BigDecimal getPlatCost() {

        return platCost;
    }

    public void setPlatCost(BigDecimal platCost) {

        this.platCost = platCost;
    }

    @Basic
    @Column(name = "plat_profit", scale = 2)
    public BigDecimal getPlatProfit() {

        return platProfit;
    }

    public void setPlatProfit(BigDecimal platProfit) {

        this.platProfit = platProfit;
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
    @Column(name = "pay_success_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getPaySuccessTime() {

        return paySuccessTime;
    }

    public void setPaySuccessTime(Date paySuccessTime) {

        this.paySuccessTime = paySuccessTime;
    }

    @Basic
    @Column(name = "complete_time")
    @Temporal(TemporalType.TIMESTAMP)

    public Date getCompleteTime() {

        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {

        this.completeTime = completeTime;
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
    @Column(name = "success_refund_amount", scale = 2)
    public BigDecimal getSuccessRefundAmount() {

        return successRefundAmount;
    }

    public void setSuccessRefundAmount(BigDecimal successRefundAmount) {

        this.successRefundAmount = successRefundAmount;
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
    @Column(name = "order_from")
    public String getOrderFrom() {

        return orderFrom;
    }

    public void setOrderFrom(String orderFrom) {

        this.orderFrom = orderFrom;
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
    @Column(name = "remark")
    public String getRemark() {

        return remark;
    }

    public void setRemark(String remark) {

        this.remark = remark;
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
    @Column(name = "bank_return_msg")
    public String getBankReturnMsg() {

        return bankReturnMsg;
    }

    public void setBankReturnMsg(String bankReturnMsg) {

        this.bankReturnMsg = bankReturnMsg;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TradePaymentRecordEntity that = (TradePaymentRecordEntity) o;
        return version == that.version &&
                Objects.equals(id, that.id) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(status, that.status) &&
                Objects.equals(editor, that.editor) &&
                Objects.equals(creater, that.creater) &&
                Objects.equals(editTime, that.editTime) &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(merchantOrderNo, that.merchantOrderNo) &&
                Objects.equals(trxNo, that.trxNo) &&
                Objects.equals(bankOrderNo, that.bankOrderNo) &&
                Objects.equals(bankTrxNo, that.bankTrxNo) &&
                Objects.equals(merchantName, that.merchantName) &&
                Objects.equals(merchantNo, that.merchantNo) &&
                Objects.equals(payerUserNo, that.payerUserNo) &&
                Objects.equals(payerName, that.payerName) &&
                Objects.equals(payerPayAmount, that.payerPayAmount) &&
                Objects.equals(payerFee, that.payerFee) &&
                Objects.equals(payerAccountType, that.payerAccountType) &&
                Objects.equals(receiverUserNo, that.receiverUserNo) &&
                Objects.equals(receiverName, that.receiverName) &&
                Objects.equals(receiverPayAmount, that.receiverPayAmount) &&
                Objects.equals(receiverFee, that.receiverFee) &&
                Objects.equals(receiverAccountType, that.receiverAccountType) &&
                Objects.equals(orderIp, that.orderIp) &&
                Objects.equals(orderRefererUrl, that.orderRefererUrl) &&
                Objects.equals(orderAmount, that.orderAmount) &&
                Objects.equals(platIncome, that.platIncome) &&
                Objects.equals(feeRate, that.feeRate) &&
                Objects.equals(platCost, that.platCost) &&
                Objects.equals(platProfit, that.platProfit) &&
                Objects.equals(returnUrl, that.returnUrl) &&
                Objects.equals(notifyUrl, that.notifyUrl) &&
                Objects.equals(payWayCode, that.payWayCode) &&
                Objects.equals(payWayName, that.payWayName) &&
                Objects.equals(paySuccessTime, that.paySuccessTime) &&
                Objects.equals(completeTime, that.completeTime) &&
                Objects.equals(isRefund, that.isRefund) &&
                Objects.equals(refundTimes, that.refundTimes) &&
                Objects.equals(successRefundAmount, that.successRefundAmount) &&
                Objects.equals(trxType, that.trxType) &&
                Objects.equals(orderFrom, that.orderFrom) &&
                Objects.equals(payTypeCode, that.payTypeCode) &&
                Objects.equals(payTypeName, that.payTypeName) &&
                Objects.equals(fundIntoType, that.fundIntoType) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(field1, that.field1) &&
                Objects.equals(field2, that.field2) &&
                Objects.equals(field3, that.field3) &&
                Objects.equals(field4, that.field4) &&
                Objects.equals(field5, that.field5) &&
                Objects.equals(bankReturnMsg, that.bankReturnMsg);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, version, createTime, status, editor, creater, editTime, productName, merchantOrderNo, trxNo, bankOrderNo, bankTrxNo, merchantName, merchantNo, payerUserNo, payerName, payerPayAmount, payerFee, payerAccountType, receiverUserNo, receiverName, receiverPayAmount, receiverFee, receiverAccountType, orderIp, orderRefererUrl, orderAmount, platIncome, feeRate, platCost, platProfit, returnUrl, notifyUrl, payWayCode, payWayName, paySuccessTime, completeTime, isRefund, refundTimes, successRefundAmount, trxType, orderFrom, payTypeCode, payTypeName, fundIntoType, remark, field1, field2, field3, field4, field5, bankReturnMsg);
    }
}
