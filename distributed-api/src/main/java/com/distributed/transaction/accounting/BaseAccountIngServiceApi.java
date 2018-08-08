package com.distributed.transaction.accounting;

import com.distributed.transaction.accounting.api.AccountingReqT;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 会计凭证服务接口
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-08-下午 2:51
 */
@FeignClient("DISTRIBUTED-ACCOUNTING")
public interface BaseAccountIngServiceApi {

    @PostMapping("/accounting/create/voucher")
    public void createAccountVoucher(AccountingReqT reqT);

}
