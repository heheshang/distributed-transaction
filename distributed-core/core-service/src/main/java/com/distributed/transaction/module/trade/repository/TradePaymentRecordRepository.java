package com.distributed.transaction.module.trade.repository;

import com.distributed.transaction.base.BaseRepository;
import com.distributed.transaction.module.trade.domain.TradePaymentRecordEntity;
import org.springframework.stereotype.Repository;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-25-下午 1:20
 */
@Repository
public interface TradePaymentRecordRepository extends BaseRepository<TradePaymentRecordEntity, String> {

    /**
     * 根据银行订单号和支付流水号获取交易记录信息
     * @param trxNo 支付流水号
     * @param bankNo 银行订单号
     * @return
     */
    TradePaymentRecordEntity getByTrxNoAndBankOrderNo(String trxNo, String bankNo);
}
