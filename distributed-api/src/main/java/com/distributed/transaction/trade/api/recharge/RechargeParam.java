package com.distributed.transaction.trade.api.recharge;


import com.distributed.transaction.BaseParam;
import com.distributed.transaction.enums.PublicEnum;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 充值提交参数
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-04-上午 11:00
 */
@Data
@ToString
public class RechargeParam extends BaseParam {

    /**
     * 商品名称
     **/
    private String productName;

    /**
     * 商户订单编号
     **/
    private String merchantOrderNo;

    /**
     * 订单金额
     **/
    private BigDecimal orderAmount;

    /**
     * 订单来源
     **/
    private String orderFrom;

    /**
     * 商户名称
     **/
    private String merchantName;

    /**
     * 商户编号
     **/
    private String merchantNo;

    /**
     * 订单时间
     **/
    private Date orderTime;

    /**
     * 订单日期
     **/
    private Date orderDate;

    /**
     * 订单来源IP
     **/
    private String orderIp;

    /**
     * 页面链接
     **/
    private String orderRefererUrl;

    /**
     * 页面回调通知地址
     **/
    private String returnUrl;

    /**
     * 后台异步通知地址
     **/
    private String notifyUrl;

    /**
     * 订单撤销原因
     **/
    private String cancelReason;

    /**
     * 订单有效期
     **/
    private Integer orderPeriod;

    /**
     * 订单到期时间
     **/
    private Date expireTime;



    /**
     * 支付方式名称
     **/
    private String payWayName;

    /**
     * 备注
     **/
    private String remark;

    /**
     * 交易业务类型
     **/
    private String trxType;



    /**
     * 支付方式类型名称
     **/
    private String payTypeName;

    /**
     * 资金流入类型
     **/
    private String fundIntoType;

    /**
     * 是否退款
     **/
    private String isRefund = PublicEnum.NO.name();

    /**
     * 退款次数
     **/
    private Short refundTimes;

    /**
     * 成功退款金额
     **/
    private BigDecimal successRefundAmount;

    /**
     * 扩展字段1
     **/
    private String field1;

    /**
     * 扩展字段2
     **/
    private String field2;

    /**
     * 扩展字段3
     **/
    private String field3;

    /**
     * 扩展字段4
     **/
    private String field4;

    /**
     * 扩展字段5
     **/
    private String field5;
}
