package com.distributed.transaction.service;

import com.distributed.transaction.accounting.api.AccountingReqT;
import com.distributed.transaction.accounting.api.AccountingResT;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-08-下午 2:56
 */
public interface IAccountingVoucherService {


    AccountingResT createAccountingVoucher(AccountingReqT accountingReq);
}
