package com.distributed.transaction.entity.m;

import com.distributed.transaction.enums.convert.AreadlyDeadEnumConverter;
import com.distributed.transaction.enums.convert.ConsumerQueueEnumConverter;
import com.distributed.transaction.enums.convert.MsgDataTypeEnumConverter;
import com.distributed.transaction.utils.AreadlyDeadEnum;
import com.distributed.transaction.utils.ConsumerQueueEnum;
import com.distributed.transaction.utils.MsgDataTypeEnum;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-05-上午 11:19
 * {@link DynamicUpdate} 必须配合{@link org.springframework.transaction.annotation.Transactional} 在方法上使用才可以
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "transaction_message")
@Data
public class TransactionMessage implements Serializable {

    /**
     * 主键ID
     */
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "ID")
    private String id;

    /**
     * 版本号
     */
    @Column(name = "version", length = 11, nullable = false)
    @Version
    private Integer version;

    /**
     * 修改者
     */
    @Column(name = "editor", length = 100, nullable = true)
    private String editor;

    /**
     * 创建者
     */
    @Column(name = "creater", length = 100, nullable = true)
    private String creater;

    /**
     * 最后修改时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "edit_time", nullable = true)
    @UpdateTimestamp
    private Date editTime;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createTime;

    /**
     * 消息ID
     */
    @Column(name = "message_id", length = 50, nullable = false)
    private String messageId;

    /**
     * 消息内容
     */
    @Column(name = "message_body", nullable = false)
    private String messageBody;

    /**
     * 消息数据类型
     */
    @Column(name = "message_data_type")
    @Convert(converter = MsgDataTypeEnumConverter.class)
    private MsgDataTypeEnum msgDataType;

    /**
     * 消费队列
     */
    @Column(name = "consumer_queue", nullable = false)
    @Convert(converter = ConsumerQueueEnumConverter.class)
    private ConsumerQueueEnum consumerQueue;

    /**
     * 消息重发次数
     */
    @Column(name = "message_send_times", nullable = false)
    private int messageSendTimes;

    /**
     * 是否死亡
     */
    @Column(name = "areadly_dead", length = 20, nullable = false)
    @Convert(converter = AreadlyDeadEnumConverter.class)
    private AreadlyDeadEnum areadlyDead;

    /**
     * 状态
     */
    @Column(name = "status", length = 20, nullable = false)
    private String status;

    /**
     * 备注
     */
    @Column(name = "remark", length = 200)
    private String remark;

    /**
     * 扩展字段1
     */
    @Column(name = "field1", length = 200)
    private String field1;

    /**
     * 扩展字段2
     */
    @Column(name = "field2", length = 200)
    private String field2;

    /**
     * 扩展字段3
     */
    @Column(name = "field3", length = 200)
    private String field3;

    @PreUpdate
    private void preUpdate() {

        this.createTime = new Date();
    }
}
