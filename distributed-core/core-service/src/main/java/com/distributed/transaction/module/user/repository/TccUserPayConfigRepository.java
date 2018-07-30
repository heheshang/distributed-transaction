package com.distributed.transaction.module.user.repository;

import com.distributed.transaction.base.BaseRepository;
import com.distributed.transaction.module.user.domain.TccUserPayConfigEntity;
import org.springframework.stereotype.Repository;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-30-下午 3:03
 */
@Repository
public interface TccUserPayConfigRepository extends BaseRepository<TccUserPayConfigEntity, String> {

    public TccUserPayConfigEntity getByPayKey(String payKey);
}
