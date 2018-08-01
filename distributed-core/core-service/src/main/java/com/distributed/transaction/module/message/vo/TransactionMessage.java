package com.distributed.transaction.module.message.vo;

import com.distributed.transaction.enums.message.AreadlyDeadEnum;
import com.distributed.transaction.enums.message.MessageMqQueueEnum;
import com.distributed.transaction.enums.message.MessageStatusEnum;
import com.distributed.transaction.enums.message.MsgDataTypeEnum;
import com.distributed.transaction.enums.message.NotifyDestinationNameEnum;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-01-下午 3:34
 */
@Data
@ToString
public class TransactionMessage implements Serializable {

    /**
     * 主键ID
     */

    private String id;

    /**
     * 版本号
     */

    private Integer version;

    /**
     * 修改者
     */
    private String editor;

    /**
     * 创建者
     */
    private String creater;

    /**
     * 最后修改时间
     */
    private Date editTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 消息ID
     */
    private String messageId;

    /**
     * 消息内容
     */
    private String messageBody;

    /**
     * 消息数据类型
     */
    private MsgDataTypeEnum msgDataType;

    /**
     * 消费队列
     */
    private NotifyDestinationNameEnum consumerQueue;

    /**
     * 消息重发次数
     */
    private int messageSendTimes;

    /**
     * 是否死亡
     */
    private AreadlyDeadEnum areadlyDead;

    /**
     * 状态
     */
    private MessageStatusEnum status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 扩展字段1
     */
    private String field1;

    /**
     * 扩展字段2
     */
    private String field2;

    /**
     * 扩展字段3
     */
    private String field3;
}
