package com.distributed.transaction.tran;

import com.distributed.transaction.enums.message.AreadlyDeadEnum;
import com.distributed.transaction.enums.message.MessageMqQueueEnum;
import com.distributed.transaction.enums.message.MsgDataTypeEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-05-上午 11:19
 */
@Data
public class TransactionMessageVo implements Serializable {


    private String id;


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
    private MessageMqQueueEnum consumerQueue;

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
    private String status;

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
