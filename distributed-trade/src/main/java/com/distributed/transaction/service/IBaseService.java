package com.distributed.transaction.service;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-26-上午 9:04
 */
public interface IBaseService<TradeReq,TradeRes> {

    public TradeRes process(TradeReq tradeReq);
}
