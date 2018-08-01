package com.distributed.transaction.module.gateway.repository;

import com.distributed.transaction.module.gateway.domain.TccGatewayRecordEntity;
import com.distributed.transaction.base.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-20-下午 1:56
 */
@Repository
public interface TccGatewayRecordRepository extends BaseRepository<TccGatewayRecordEntity, String>  {

    public TccGatewayRecordEntity findByOrderNoAndPayKey(String orderNo, String payKey);


}
