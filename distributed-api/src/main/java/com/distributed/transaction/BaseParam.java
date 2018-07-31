package com.distributed.transaction;

import com.distributed.transaction.utils.PayTypeEnum;
import com.distributed.transaction.utils.PayWayEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-04-上午 10:26
 */
@Data
public class BaseParam implements Serializable {

    //交易时间
    private Date txnTm;

    //网关流水号
    private String transSeqNo;

    //平台ID
    private String payKey;

    /**
     * 支付通道编号
     **/
    private String payWayCode;
    /**
     * 支付方式类型编码
     **/
    private String payTypeCode;

}
