package com.distributed.transaction.api.trans;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-04-上午 10:26
 */
@Data
@ToString
public class ResT<T extends BaseMessage> implements Serializable {

    private boolean success;

    private String resultCode;

    private String resultDesc;

    private String procCode;

    private String procDesc;

    private T message;
}
