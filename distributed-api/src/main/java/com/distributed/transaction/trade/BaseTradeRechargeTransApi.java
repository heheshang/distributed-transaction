package com.distributed.transaction.trade;

import com.distributed.transaction.trade.api.TradeReq;
import com.distributed.transaction.trade.api.TradeRes;
import com.distributed.transaction.trade.api.banknotify.BankNotifyMessage;
import com.distributed.transaction.trade.api.banknotify.BankNotifyParam;
import com.distributed.transaction.trade.api.recharge.RechargeMessage;
import com.distributed.transaction.trade.api.recharge.RechargeParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 创建订单fegin接口
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-24-下午 4:11
 */
@FeignClient("DISTRIBUTED-TRADE")
public interface BaseTradeRechargeTransApi {

    /**
     * 充值
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "/trade/recharge", method = RequestMethod.POST)
    TradeRes<RechargeMessage> recharge(TradeReq<RechargeParam> req);

    /**
     * 处理银行通知接口
     *
     * @param tradeReq
     * @return
     */
    @RequestMapping(value = "/bank/message", method = RequestMethod.POST)
    TradeRes<BankNotifyMessage> bankMessageHandle(TradeReq<BankNotifyParam> tradeReq);

}
