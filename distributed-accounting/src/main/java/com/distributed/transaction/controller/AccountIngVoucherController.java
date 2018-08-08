package com.distributed.transaction.controller;

import com.distributed.transaction.service.IAccountingVoucherService;
import com.distributed.transaction.accounting.api.AccountingReqT;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-08-下午 2:59
 */
@RestController
@RequestMapping("/accounting")
@Log4j2
public class AccountIngVoucherController {

    @Autowired
    IAccountingVoucherService accountingVoucherService;

    @PostMapping("/create/voucher")
    public void createAccountVoucher(@RequestBody AccountingReqT accountingReq) {
        log.info("会计凭证服务收到创建会计服务记录请求【{}】",accountingReq.toString());


        accountingVoucherService.createAccountingVoucher(accountingReq);
    }
}
