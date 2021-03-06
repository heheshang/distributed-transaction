package com.distributed.transaction.trade.api;

import com.distributed.transaction.BaseMessage;
import lombok.Data;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-24-下午 4:20
 */
@Data
public class TradeRes<M extends BaseMessage> {

    private M message;

    private Boolean success;

    private String procCode;

    private String procDesc;

}
