package com.distributed.transaction.exception;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-10-上午 11:41
 */
public class BeanMappingException extends RuntimeException {

    public BeanMappingException() {
        this("Bean对象映射异常");
    }

    public BeanMappingException(String message) {
        super(message);
    }

    public BeanMappingException(Throwable cause) {
        super(cause);
    }

    public BeanMappingException(String message, Throwable cause) {
        super(message, cause);
    }
}
