package com.distributed.transaction.module.product.repository;

import com.distributed.transaction.base.BaseRepository;
import com.distributed.transaction.module.product.domain.TccPayProductEntity;
import org.springframework.stereotype.Repository;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-25-下午 1:20
 */
@Repository
public interface TccPayProductRepository extends BaseRepository<TccPayProductEntity, String> {


}
