package com.distributed.transaction.module.message.repository;

import com.distributed.transaction.base.BaseRepository;
import com.distributed.transaction.module.message.domain.TransactionMessageEntity;
import org.springframework.stereotype.Repository;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-01-下午 3:33
 */
@Repository
public interface TransactionMessageRepository extends BaseRepository<TransactionMessageEntity, String> {

}
