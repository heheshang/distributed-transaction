package com.distributed.transaction.module.account.domain;

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
@Table(name = "pay_account")
@SelectBeforeUpdate
@DynamicInsert
@DynamicUpdate
public class PayAccountEntity implements Serializable {

    private String id;

    private Date createTime;

    private Date editTime;

    private Integer version;

    private String remark;

    private String accountNo;

    private BigDecimal balance;

    private BigDecimal unbalance;

    private BigDecimal securityMoney;

    private String status;

    private BigDecimal totalIncome;

    private BigDecimal totalExpend;

    private BigDecimal todayIncome;

    private BigDecimal todayExpend;

    private String accountType;

    private BigDecimal settAmount;

    private String userNo;


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
    public Date getCreateTime() {

        return createTime;
    }

    public void setCreateTime(Date createTime) {

        this.createTime = createTime;
    }

    @Basic
    @Column(name = "edit_time")
    public Date getEditTime() {

        return editTime;
    }

    public void setEditTime(Date editTime) {

        this.editTime = editTime == null ? new Date() : editTime;
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
    @Column(name = "balance")
    public BigDecimal getBalance() {

        return balance;
    }

    public void setBalance(BigDecimal balance) {

        this.balance = balance;
    }

    @Basic
    @Column(name = "unbalance")
    public BigDecimal getUnbalance() {

        return unbalance;
    }

    public void setUnbalance(BigDecimal unbalance) {

        this.unbalance = unbalance;
    }

    @Basic
    @Column(name = "security_money")
    public BigDecimal getSecurityMoney() {

        return securityMoney;
    }

    public void setSecurityMoney(BigDecimal securityMoney) {

        this.securityMoney = securityMoney;
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
    @Column(name = "total_income")
    public BigDecimal getTotalIncome() {

        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {

        this.totalIncome = totalIncome;
    }

    @Basic
    @Column(name = "total_expend")
    public BigDecimal getTotalExpend() {

        return totalExpend;
    }

    public void setTotalExpend(BigDecimal totalExpend) {

        this.totalExpend = totalExpend;
    }

    @Basic
    @Column(name = "today_income")
    public BigDecimal getTodayIncome() {

        return todayIncome;
    }

    public void setTodayIncome(BigDecimal todayIncome) {

        this.todayIncome = todayIncome;
    }

    @Basic
    @Column(name = "today_expend")
    public BigDecimal getTodayExpend() {

        return todayExpend;
    }

    public void setTodayExpend(BigDecimal todayExpend) {

        this.todayExpend = todayExpend;
    }

    @Basic
    @Column(name = "account_type")
    public String getAccountType() {

        return accountType;
    }

    public void setAccountType(String accountType) {

        this.accountType = accountType;
    }

    @Basic
    @Column(name = "sett_amount")
    public BigDecimal getSettAmount() {

        return settAmount;
    }

    public void setSettAmount(BigDecimal settAmount) {

        this.settAmount = settAmount;
    }

    @Basic
    @Column(name = "user_no")
    public String getUserNo() {

        return userNo;
    }

    public void setUserNo(String userNo) {

        this.userNo = userNo;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PayAccountEntity that = (PayAccountEntity) o;
        return version == that.version &&
                Objects.equals(id, that.id) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(editTime, that.editTime) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(accountNo, that.accountNo) &&
                Objects.equals(balance, that.balance) &&
                Objects.equals(unbalance, that.unbalance) &&
                Objects.equals(securityMoney, that.securityMoney) &&
                Objects.equals(status, that.status) &&
                Objects.equals(totalIncome, that.totalIncome) &&
                Objects.equals(totalExpend, that.totalExpend) &&
                Objects.equals(todayIncome, that.todayIncome) &&
                Objects.equals(todayExpend, that.todayExpend) &&
                Objects.equals(accountType, that.accountType) &&
                Objects.equals(settAmount, that.settAmount) &&
                Objects.equals(userNo, that.userNo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, createTime, editTime, version, remark, accountNo, balance, unbalance, securityMoney, status, totalIncome, totalExpend, todayIncome, todayExpend, accountType, settAmount, userNo);
    }
}
