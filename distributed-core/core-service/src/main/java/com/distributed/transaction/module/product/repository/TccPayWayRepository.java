package com.distributed.transaction.module.product.repository;

import com.distributed.transaction.base.BaseRepository;
import com.distributed.transaction.module.product.domain.TccPayWayEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-25-下午 1:20
 */
@Repository
public interface TccPayWayRepository extends BaseRepository<TccPayWayEntity, String> {

    /**
     * @param payProductCode
     * @param payWayCode
     * @param payTypeCode
     * @return
     */
    @Query("select t from TccPayWayEntity t where t.payProductCode=:payProductCode and t.payWayCode=:payWayCode and t.payTypeCode=:payTypeCode and t.status='ACTIVE'")
    public TccPayWayEntity getByPayWayTypeCode(@Param("payProductCode") String payProductCode, @Param("payWayCode") String payWayCode, @Param("payTypeCode") String payTypeCode);
}
