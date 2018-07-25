package com.distributed.transaction.api.trade.repository;

import com.distributed.transaction.api.repository.BaseRepository;
import com.distributed.transaction.api.trade.domain.TradePaymentRecordEntity;
import org.springframework.stereotype.Repository;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-25-下午 1:20
 */
@Repository
public interface TradePaymentRecordRepository extends BaseRepository<TradePaymentRecordEntity, String> {

}
