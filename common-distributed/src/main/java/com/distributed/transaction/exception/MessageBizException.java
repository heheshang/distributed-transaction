
package com.distributed.transaction.exception;

import lombok.extern.log4j.Log4j2;

/**
 * 交易服务异常
 */
@Log4j2
public class MessageBizException extends DistributedException {

    /**
     *
     */
    private static final long serialVersionUID = 3536909333010163563L;
    /** 保存的消息为空 **/
    public static final String  SAVA_MESSAGE_IS_NULL = "8001";

    /** 消息的消费队列为空 **/
    public static final String MESSAGE_CONSUMER_QUEUE_IS_NULL = "8002";

    /**
     * 支付订单号重复
     **/
    public static final String TRADE_ORDER_NO_REPEAT_ERROR = "101";

    /**
     * 错误的支付方式
     **/
    public static final String TRADE_PAY_WAY_ERROR = "102";

    /**
     * 微信异常
     **/
    public static final String TRADE_WEIXIN_ERROR = "103";

    /**
     * 订单异常
     **/
    public static final String TRADE_ORDER_ERROR = "104";

    /**
     * 交易记录状态不为成功
     **/
    public static final String TRADE_ORDER_STATUS_NOT_SUCCESS = "105";

    /**
     * 支付宝异常
     **/
    public static final String TRADE_ALIPAY_ERROR = "106";

    /**
     * 参数异常
     **/
    public static final String TRADE_PARAM_ERROR = "107";

    /**
     * 交易系统异常
     **/
    public static final String MESSAGE_SYSTEM_ERROR = "108";


    public MessageBizException() {

    }

    public MessageBizException(String code, String msgFormat, Object... args) {

        super(code, msgFormat, args);
    }

    public MessageBizException(String code, String msg) {

        super(code, msg);
    }

    public MessageBizException print() {

        log.info("==>BizException, errCode:" + this.errCode + ", errMsg:" + this.errMsg);
        return this;
    }
}
