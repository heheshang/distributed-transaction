package com.distributed.transaction.trade.api.recharge;


import com.distributed.transaction.BaseMessage;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-04-上午 11:02
 */
@Data
@ToString
public class RechargeMessage extends BaseMessage {


    /**
     * 商户编号
     **/
    private String merchantNo;

    /**
     * 商户订单编号
     **/
    private String merchantOrderNo;

    /**
     * 订单金额
     **/
    private BigDecimal orderAmount;

    /**
     * 交易状态
     */
    private String status;

    /**
     * 交易号
     */
    private String trxNo;

    /**
     * 银行订单号
     */
    private String bankOrderNo;
}
