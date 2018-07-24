package com.distributed.transaction.trade.api;

import lombok.Data;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-24-下午 4:19
 */
@Data
public class TradeReq<T extends BaseParam> {

    private T t;
}
