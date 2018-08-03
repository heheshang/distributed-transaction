package com.distributed.transaction.trade.api.banknotify;

import com.distributed.transaction.BaseParam;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Map;

/**
 * 银行通知 req
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-03-上午 11:37
 */
@Data
@ToString
@AllArgsConstructor
public class BankNotifyParam extends BaseParam {

    private Map<String, String> notifyMap = Maps.newHashMap();
}
