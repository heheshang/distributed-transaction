package com.distributed.transaction.module.trade.repository;

import com.distributed.transaction.base.BaseRepository;
import com.distributed.transaction.module.trade.domain.TradePaymentOrderEntity;
import org.springframework.stereotype.Repository;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-25-下午 1:20
 */
@Repository
public interface TradePaymentOrderRepository extends BaseRepository<TradePaymentOrderEntity, String> {

    /**
     *
     * @param merchantNo 商户编号
     * @param merchantOrderNo 商户订单编号
     * @return
     */
    TradePaymentOrderEntity getByMerchantNoAndMerchantOrderNo(String merchantNo,String merchantOrderNo);



}
