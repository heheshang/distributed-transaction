package com.distributed.transaction.module.user.domain;

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
@Table(name = "tcc_user_info", schema = "pay_product", catalog = "")
public class TccUserInfoEntity {

    private String id;

    private Timestamp createTime;

    private String status;

    private String userNo;

    private String userName;

    private String accountNo;

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
    @Column(name = "status")
    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {

        this.status = status;
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
    @Column(name = "account_no")
    public String getAccountNo() {

        return accountNo;
    }

    public void setAccountNo(String accountNo) {

        this.accountNo = accountNo;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TccUserInfoEntity that = (TccUserInfoEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(status, that.status) &&
                Objects.equals(userNo, that.userNo) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(accountNo, that.accountNo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, createTime, status, userNo, userName, accountNo);
    }
}
