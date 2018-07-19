package com.distributed.transaction.exception;

import lombok.Data;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-04-下午 1:13
 */
@Data
public class DistributedExceprion extends Exception {

    /**
     * 模块名，抛出异常的类
     */
    @SuppressWarnings("rawtypes")
    protected Class errClass = null;

    /**
     * 错误码
     */
    protected String errCode = null;

    /**
     * 错误描述信息
     */
    protected String errMsg = null;

    public DistributedExceprion() {

        super();
    }


    public DistributedExceprion(String errCode, Class errClass) {

        super();
        this.errCode = errCode;
        this.errClass = errClass;
    }

    public DistributedExceprion(String errCode, Exception e) {

        super(e);
        this.errCode = errCode;
    }

    public DistributedExceprion(String errCode, String errMsg, Class errClass,
                                Exception e) {

        super(errMsg, e);
        this.errClass = errClass;
        this.errCode = errCode;
        this.errMsg = errMsg;
    }


}
