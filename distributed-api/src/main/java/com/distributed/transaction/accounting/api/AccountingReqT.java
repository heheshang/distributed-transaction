package com.distributed.transaction.accounting.api;

import lombok.Data;
import lombok.ToString;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-08-下午 3:02
 */
@Data
@ToString
public class AccountingReqT<P> {

    private P param;
}
