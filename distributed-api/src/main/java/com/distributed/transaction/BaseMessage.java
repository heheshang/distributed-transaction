package com.distributed.transaction;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-04-上午 10:42
 */
@Data
public class BaseMessage implements Serializable {


    private String errorReason;

    private String errorCode;

    private Class errorClass;
}
