package com.distributed.transaction.interceptor;

import com.distributed.transaction.BaseParam;
import com.distributed.transaction.annotations.DataSource;
import com.distributed.transaction.module.trade.repository.TradePaymentOrderRepository;
import com.distributed.transaction.module.trade.repository.TradePaymentRecordRepository;
import com.distributed.transaction.module.user.domain.TccUserPayConfigEntity;
import com.distributed.transaction.module.user.repository.TccUserPayConfigRepository;
import com.distributed.transaction.trade.api.recharge.RechargeParam;
import com.distributed.transaction.utils.TradeDataSourceType;
import lombok.extern.log4j.Log4j2;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-30-下午 3:07
 */
@Component
@Log4j2
public class TradeTranService<P extends BaseParam> {

    @Autowired
    private TradePaymentOrderRepository tradePaymentOrderRepository;

    @Autowired
    private TradePaymentRecordRepository tradePaymentRecordRepository;

    @Autowired
    private TccUserPayConfigRepository tccUserPayConfigRepository;
    @Autowired
    private DozerBeanMapper mapper;

    @DataSource(TradeDataSourceType.SLAVE)
    public RechargeParam bulidTradePaymentOrder(P p) {

        RechargeParam param = (RechargeParam) p;

        TccUserPayConfigEntity userPayConfig = tccUserPayConfigRepository.getByPayKey(p.getPayKey());

        userPayConfig.setEditTime(new Timestamp(System.currentTimeMillis()));
        log.info("获取用户配置信息为[{}]", userPayConfig);

        return param;
    }
}
