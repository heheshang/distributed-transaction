package com.distributed.transaction.module.account.repository;

import com.distributed.transaction.base.BaseRepository;
import com.distributed.transaction.module.account.domain.PayAccountHistoryEntity;
import org.springframework.stereotype.Repository;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-06-下午 1:51
 */
@Repository
public interface PayAccountHistoryRepository extends BaseRepository<PayAccountHistoryEntity, String> {

}
