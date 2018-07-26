package com.distributed.transaction;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-04-上午 10:26
 */
@Data
public  class BaseParam implements Serializable {

    //交易时间
    private Date txnTm;

    //网关流水号
    private String transSeqNo;

    //平台ID
    private String platformId;

    //平台商户ID
    private String custId;
}
