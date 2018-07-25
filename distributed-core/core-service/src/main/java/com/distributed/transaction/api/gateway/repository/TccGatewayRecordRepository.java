package com.distributed.transaction.api.gateway.repository;

import com.distributed.transaction.api.gateway.domain.TccGatewayRecord;
import com.distributed.transaction.api.repository.BaseRepository;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.stereotype.Repository;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-20-下午 1:56
 */
@Repository
public interface TccGatewayRecordRepository extends BaseRepository<TccGatewayRecord, String>  {

    public TccGatewayRecord findByOrderNoAndPayKey(String orderNo, String payKey);


}
