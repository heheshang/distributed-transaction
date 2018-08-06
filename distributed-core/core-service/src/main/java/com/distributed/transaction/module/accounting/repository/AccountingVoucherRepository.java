package com.distributed.transaction.module.accounting.repository;

import com.distributed.transaction.base.BaseRepository;
import com.distributed.transaction.module.accounting.domain.AccountingVoucherEntity;
import org.springframework.stereotype.Repository;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-06-下午 1:48
 */
@Repository
public interface AccountingVoucherRepository extends BaseRepository<AccountingVoucherEntity, String> {

}
