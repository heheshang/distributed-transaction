package com.distributed.transaction.module.account.domain;

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
import javax.persistence.Version;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-06-下午 1:31
 */
@Entity
@Table(name = "pay_account_history")
@SelectBeforeUpdate
@DynamicInsert
@DynamicUpdate
public class PayAccountHistoryEntity implements Serializable {

    private String id;

    private Date createTime;

    private Date editTime;

    private Integer version;

    private String remark;

    private String accountNo;

    private BigDecimal amount;

    private BigDecimal balance;

    private String fundDirection;

    private String isAllowSett;

    private String isCompleteSett;

    private String requestNo;

    private String bankTrxNo;

    private String trxType;

    private Integer riskDay;

    private String userNo;

    private String status;


    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "id")
    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    @Basic
    @Column(name = "create_time")
    @CreationTimestamp
    public Date getCreateTime() {

        return createTime;
    }

    public void setCreateTime(Date createTime) {

        this.createTime = createTime;
    }

    @Basic
    @Column(name = "edit_time")
    @UpdateTimestamp
    public Date getEditTime() {

        return editTime;
    }

    public void setEditTime(Date editTime) {

        this.editTime = editTime==null?new Date():editTime;
    }

    @Basic
    @Column(name = "version")
    @Version
    public Integer getVersion() {

        return version;
    }

    public void setVersion(Integer version) {

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
    @Column(name = "account_no")
    public String getAccountNo() {

        return accountNo;
    }

    public void setAccountNo(String accountNo) {

        this.accountNo = accountNo;
    }

    @Basic
    @Column(name = "amount")
    public BigDecimal getAmount() {

        return amount;
    }

    public void setAmount(BigDecimal amount) {

        this.amount = amount;
    }

    @Basic
    @Column(name = "balance")
    public BigDecimal getBalance() {

        return balance;
    }

    public void setBalance(BigDecimal balance) {

        this.balance = balance;
    }

    @Basic
    @Column(name = "fund_direction")
    public String getFundDirection() {

        return fundDirection;
    }

    public void setFundDirection(String fundDirection) {

        this.fundDirection = fundDirection;
    }

    @Basic
    @Column(name = "is_allow_sett")
    public String getIsAllowSett() {

        return isAllowSett;
    }

    public void setIsAllowSett(String isAllowSett) {

        this.isAllowSett = isAllowSett;
    }

    @Basic
    @Column(name = "is_complete_sett")
    public String getIsCompleteSett() {

        return isCompleteSett;
    }

    public void setIsCompleteSett(String isCompleteSett) {

        this.isCompleteSett = isCompleteSett;
    }

    @Basic
    @Column(name = "request_no")
    public String getRequestNo() {

        return requestNo;
    }

    public void setRequestNo(String requestNo) {

        this.requestNo = requestNo;
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
    @Column(name = "trx_type")
    public String getTrxType() {

        return trxType;
    }

    public void setTrxType(String trxType) {

        this.trxType = trxType;
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
    @Column(name = "user_no")
    public String getUserNo() {

        return userNo;
    }

    public void setUserNo(String userNo) {

        this.userNo = userNo;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PayAccountHistoryEntity that = (PayAccountHistoryEntity) o;
        return version == that.version &&
                Objects.equals(id, that.id) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(editTime, that.editTime) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(accountNo, that.accountNo) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(balance, that.balance) &&
                Objects.equals(fundDirection, that.fundDirection) &&
                Objects.equals(isAllowSett, that.isAllowSett) &&
                Objects.equals(isCompleteSett, that.isCompleteSett) &&
                Objects.equals(requestNo, that.requestNo) &&
                Objects.equals(bankTrxNo, that.bankTrxNo) &&
                Objects.equals(trxType, that.trxType) &&
                Objects.equals(riskDay, that.riskDay) &&
                Objects.equals(userNo, that.userNo) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, createTime, editTime, version, remark, accountNo, amount, balance, fundDirection, isAllowSett, isCompleteSett, requestNo, bankTrxNo, trxType, riskDay, userNo, status);
    }
}
