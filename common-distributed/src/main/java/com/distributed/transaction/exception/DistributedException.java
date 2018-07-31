package com.distributed.transaction.exception;

import lombok.Data;

/**
 * 在 Spring AOP中调用一个方法来进行数据验证
 * <p>
 * 一旦数据验证失败，抛出一个自定义的异常。然而，却抛出了java.lang.reflect.UndeclaredThrowableException
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 查了一下，因为我的自定义异常继承Exception类。把它改为继承RuntimeException，再抛出的异常就是自己的自定义异常了
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-04-下午 1:13
 */
@Data
public class DistributedException extends RuntimeException {

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

    public DistributedException() {

        super();
    }


    public DistributedException(String errCode, String errMsg) {

        super();
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public DistributedException(String code, String msgFormat, Object... args) {

        super(String.format(msgFormat, args));
        this.errCode = code;
        this.errMsg = String.format(msgFormat, args);
    }

    public DistributedException(String errCode, Class errClass) {

        super();
        this.errCode = errCode;
        this.errClass = errClass;
    }

    public DistributedException(String errCode, Exception e) {

        super(e);
        this.errCode = errCode;
    }

    public DistributedException(String errCode, String errMsg, Class errClass,
                                Exception e) {

        super(errMsg, e);
        this.errClass = errClass;
        this.errCode = errCode;
        this.errMsg = errMsg;
    }


}
