package com.distributed.transaction.service;

import com.distributed.transaction.enums.trade.TradeRequestTypeEnum;
import com.distributed.transaction.trade.api.TradeReq;
import com.distributed.transaction.trade.api.TradeRes;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-26-上午 9:04
 */
public interface IBaseService {

    /**
     * @param requestTypeEnum 请求类型
     * @param tradeReq 请求对象
     * @return
     */
    TradeRes process(TradeRequestTypeEnum requestTypeEnum, TradeReq tradeReq);


}
