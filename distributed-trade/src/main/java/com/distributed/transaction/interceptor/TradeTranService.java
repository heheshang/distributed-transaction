package com.distributed.transaction.interceptor;

import com.distributed.transaction.BaseParam;
import com.distributed.transaction.exception.DistributedException;
import com.distributed.transaction.module.product.repository.TccPayWayRepository;
import com.distributed.transaction.module.trade.repository.TradePaymentOrderRepository;
import com.distributed.transaction.module.trade.repository.TradePaymentRecordRepository;
import com.distributed.transaction.module.trade.vo.TradePaymentOrderVo;
import com.distributed.transaction.module.user.domain.TccUserInfoEntity;
import com.distributed.transaction.module.user.domain.TccUserPayConfigEntity;
import com.distributed.transaction.module.user.repository.TccUserInfoRepository;
import com.distributed.transaction.module.user.repository.TccUserPayConfigRepository;
import com.distributed.transaction.trade.api.recharge.RechargeParam;
import lombok.extern.log4j.Log4j2;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    private TccUserInfoRepository tccUserInfoRepository;

    @Autowired
    private TccPayWayRepository tccPayWayRepository;


    @Autowired
    private DozerBeanMapper mapper;

    @Transactional(rollbackFor = Exception.class)
    public RechargeParam bulidTradePaymentOrder(P p) {

        RechargeParam param = (RechargeParam) p;

        TccUserPayConfigEntity userPayConfig = tccUserPayConfigRepository.getByPayKey(p.getPayKey());

        if (userPayConfig == null) {
            throw new DistributedException("2222", "33333");
        }

        TccUserInfoEntity userInfoEntity = tccUserInfoRepository.getByUserNoAndStatus(userPayConfig.getUserNo(), "ACTIVE");

        if (userInfoEntity == null) {
            throw new DistributedException("2222", "33333");
        }

        log.info("获取用户配置信息为[{}]", userPayConfig.getUserNo());

        tccPayWayRepository.getByPayWayTypeCode(userPayConfig.getProductCode(), param.getPayWayCode(), param.getPayTypeCode());


        return param;
    }

    @Transactional
    public TradePaymentOrderVo bulidTradePayOrderVo() {

        TradePaymentOrderVo vo = new TradePaymentOrderVo();
        return vo;
    }
}
