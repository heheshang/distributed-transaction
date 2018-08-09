package com.distributed.transaction.module.accounting.domain;

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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-06-下午 1:39
 */
@Entity
@Table(name = "accounting_voucher")
@SelectBeforeUpdate
@DynamicInsert
@DynamicUpdate
public class AccountingVoucherEntity {

    private long id;

    private Date createTime;

    private Date editTime;

    private short entryType;

    private String requestNo;

    private short fromSystem;

    private String voucherNo;

    private Date accountingDate;

    private BigDecimal bankChangeAmount;

    private String payerAccountNo;

    private String receiverAccountNo;

    private String bankAccount;

    private String bankChannelCode;

    private BigDecimal payerChangeAmount;

    private BigDecimal receiverChangeAmount;

    private BigDecimal profit;

    private BigDecimal income;

    private BigDecimal cost;

    private String remark;

    private String bankOrderNo;

    private Short payerAccountType;

    private BigDecimal payAmount;

    private Short receiverAccountType;

    private BigDecimal receiverFee;

    private BigDecimal payerFee;


    @Id
    @Column(name = "id")
    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    @Basic
    @Column(name = "create_time", nullable = true)
    @CreationTimestamp
    public Date getCreateTime() {

        return createTime;
    }

    public void setCreateTime(Date createTime) {

        this.createTime = createTime;
    }

    @Basic
    @Column(name = "edit_time", nullable = true)
    @UpdateTimestamp
    public Date getEditTime() {

        return editTime;
    }

    public void setEditTime(Date editTime) {

        this.editTime = editTime == null ? new Date() : editTime;
    }

    @Basic
    @Column(name = "entry_type", nullable = false)
    public short getEntryType() {

        return entryType;
    }

    public void setEntryType(short entryType) {

        this.entryType = entryType;
    }

    @Basic
    @Column(name = "request_no", nullable = false, length = 32)
    public String getRequestNo() {

        return requestNo;
    }

    public void setRequestNo(String requestNo) {

        this.requestNo = requestNo;
    }

    @Basic
    @Column(name = "from_system", nullable = false)
    public short getFromSystem() {

        return fromSystem;
    }

    public void setFromSystem(short fromSystem) {

        this.fromSystem = fromSystem;
    }

    @Basic
    @Column(name = "voucher_no", nullable = true, length = 32)
    public String getVoucherNo() {

        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {

        this.voucherNo = voucherNo;
    }

    @Basic
    @Column(name = "accounting_date", nullable = true)
    public Date getAccountingDate() {

        return accountingDate;
    }

    public void setAccountingDate(Date accountingDate) {

        this.accountingDate = accountingDate;
    }

    @Basic
    @Column(name = "bank_change_amount", nullable = true, precision = 10)
    public BigDecimal getBankChangeAmount() {

        return bankChangeAmount;
    }

    public void setBankChangeAmount(BigDecimal bankChangeAmount) {

        this.bankChangeAmount = bankChangeAmount;
    }

    @Basic
    @Column(name = "payer_account_no", nullable = true, length = 20)
    public String getPayerAccountNo() {

        return payerAccountNo;
    }

    public void setPayerAccountNo(String payerAccountNo) {

        this.payerAccountNo = payerAccountNo;
    }

    @Basic
    @Column(name = "receiver_account_no", nullable = true, length = 20)
    public String getReceiverAccountNo() {

        return receiverAccountNo;
    }

    public void setReceiverAccountNo(String receiverAccountNo) {

        this.receiverAccountNo = receiverAccountNo;
    }

    @Basic
    @Column(name = "bank_account", nullable = true, length = 20)
    public String getBankAccount() {

        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {

        this.bankAccount = bankAccount;
    }

    @Basic
    @Column(name = "bank_channel_code", nullable = true, length = 32)
    public String getBankChannelCode() {

        return bankChannelCode;
    }

    public void setBankChannelCode(String bankChannelCode) {

        this.bankChannelCode = bankChannelCode;
    }

    @Basic
    @Column(name = "payer_change_amount", nullable = true, precision = 10)
    public BigDecimal getPayerChangeAmount() {

        return payerChangeAmount;
    }

    public void setPayerChangeAmount(BigDecimal payerChangeAmount) {

        this.payerChangeAmount = payerChangeAmount;
    }

    @Basic
    @Column(name = "receiver_change_amount", nullable = true, precision = 10)
    public BigDecimal getReceiverChangeAmount() {

        return receiverChangeAmount;
    }

    public void setReceiverChangeAmount(BigDecimal receiverChangeAmount) {

        this.receiverChangeAmount = receiverChangeAmount;
    }

    @Basic
    @Column(name = "profit", nullable = true, precision = 10)
    public BigDecimal getProfit() {

        return profit;
    }

    public void setProfit(BigDecimal profit) {

        this.profit = profit;
    }

    @Basic
    @Column(name = "income", nullable = true, precision = 10)
    public BigDecimal getIncome() {

        return income;
    }

    public void setIncome(BigDecimal income) {

        this.income = income;
    }

    @Basic
    @Column(name = "cost", nullable = true, precision = 10)
    public BigDecimal getCost() {

        return cost;
    }

    public void setCost(BigDecimal cost) {

        this.cost = cost;
    }

    @Basic
    @Column(name = "remark", nullable = true, length = 128)
    public String getRemark() {

        return remark;
    }

    public void setRemark(String remark) {

        this.remark = remark;
    }

    @Basic
    @Column(name = "bank_order_no", nullable = true, length = 32)
    public String getBankOrderNo() {

        return bankOrderNo;
    }

    public void setBankOrderNo(String bankOrderNo) {

        this.bankOrderNo = bankOrderNo;
    }

    @Basic
    @Column(name = "payer_account_type", nullable = true)
    public Short getPayerAccountType() {

        return payerAccountType;
    }

    public void setPayerAccountType(Short payerAccountType) {

        this.payerAccountType = payerAccountType;
    }

    @Basic
    @Column(name = "pay_amount", nullable = true, precision = 6)
    public BigDecimal getPayAmount() {

        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {

        this.payAmount = payAmount;
    }

    @Basic
    @Column(name = "receiver_account_type", nullable = true)
    public Short getReceiverAccountType() {

        return receiverAccountType;
    }

    public void setReceiverAccountType(Short receiverAccountType) {

        this.receiverAccountType = receiverAccountType;
    }

    @Basic
    @Column(name = "receiver_fee", nullable = true, precision = 6)
    public BigDecimal getReceiverFee() {

        return receiverFee;
    }

    public void setReceiverFee(BigDecimal receiverFee) {

        this.receiverFee = receiverFee;
    }

    @Basic
    @Column(name = "payer_fee", nullable = true, precision = 6)
    public BigDecimal getPayerFee() {

        return payerFee;
    }

    public void setPayerFee(BigDecimal payerFee) {

        this.payerFee = payerFee;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountingVoucherEntity that = (AccountingVoucherEntity) o;
        return id == that.id &&
                entryType == that.entryType &&
                fromSystem == that.fromSystem &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(editTime, that.editTime) &&
                Objects.equals(requestNo, that.requestNo) &&
                Objects.equals(voucherNo, that.voucherNo) &&
                Objects.equals(accountingDate, that.accountingDate) &&
                Objects.equals(bankChangeAmount, that.bankChangeAmount) &&
                Objects.equals(payerAccountNo, that.payerAccountNo) &&
                Objects.equals(receiverAccountNo, that.receiverAccountNo) &&
                Objects.equals(bankAccount, that.bankAccount) &&
                Objects.equals(bankChannelCode, that.bankChannelCode) &&
                Objects.equals(payerChangeAmount, that.payerChangeAmount) &&
                Objects.equals(receiverChangeAmount, that.receiverChangeAmount) &&
                Objects.equals(profit, that.profit) &&
                Objects.equals(income, that.income) &&
                Objects.equals(cost, that.cost) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(bankOrderNo, that.bankOrderNo) &&
                Objects.equals(payerAccountType, that.payerAccountType) &&
                Objects.equals(payAmount, that.payAmount) &&
                Objects.equals(receiverAccountType, that.receiverAccountType) &&
                Objects.equals(receiverFee, that.receiverFee) &&
                Objects.equals(payerFee, that.payerFee);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, createTime, editTime, entryType, requestNo, fromSystem, voucherNo, accountingDate, bankChangeAmount, payerAccountNo, receiverAccountNo, bankAccount, bankChannelCode, payerChangeAmount, receiverChangeAmount, profit, income, cost, remark, bankOrderNo, payerAccountType, payAmount, receiverAccountType, receiverFee, payerFee);
    }
}
