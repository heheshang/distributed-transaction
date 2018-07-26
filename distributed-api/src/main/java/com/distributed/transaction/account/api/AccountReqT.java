package com.distributed.transaction.account.api;

import com.distributed.transaction.BaseParam;
import lombok.Data;
import lombok.ToString;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-26-下午 4:37
 */
@Data
@ToString
public class AccountReqT<T extends BaseParam> {
    private T params;
}
