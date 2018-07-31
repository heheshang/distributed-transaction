package com.distributed.transaction.interceptor;

import com.distributed.transaction.BaseParam;
import com.distributed.transaction.exception.DistributedException;
import com.distributed.transaction.exception.TradeBizException;
import com.distributed.transaction.module.product.domain.TccPayWayEntity;
import com.distributed.transaction.module.product.repository.TccPayWayRepository;
import com.distributed.transaction.module.trade.domain.TradePaymentOrderEntity;
import com.distributed.transaction.module.trade.repository.TradePaymentOrderRepository;
import com.distributed.transaction.module.trade.repository.TradePaymentRecordRepository;
import com.distributed.transaction.module.trade.vo.TradePaymentOrderVo;
import com.distributed.transaction.module.user.domain.TccUserInfoEntity;
import com.distributed.transaction.module.user.domain.TccUserPayConfigEntity;
import com.distributed.transaction.module.user.repository.TccUserInfoRepository;
import com.distributed.transaction.module.user.repository.TccUserPayConfigRepository;
import com.distributed.transaction.trade.api.recharge.RechargeParam;
import com.distributed.transaction.utils.PayTypeEnum;
import com.distributed.transaction.utils.TradeStatusEnum;
import lombok.extern.log4j.Log4j2;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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

    @Autowired
    @Qualifier("entityManagerPrimary")
    private EntityManager entityManagerPrimary;


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

        TccPayWayEntity tccPayWay = tccPayWayRepository.getByPayWayTypeCode(
                userPayConfig.getProductCode(),
                param.getPayWayCode(),
                PayTypeEnum.getEnum(param.getPayWayCode()).getWay());


        TradePaymentOrderVo vo = mapper.map(param, TradePaymentOrderVo.class);

        vo.setPayWayCode(tccPayWay.getPayWayCode());

        vo.setFundIntoType(userPayConfig.getFundIntoType());

        vo.setStatus(TradeStatusEnum.WAITING_PAYMENT.name());

        String trxNo = String.valueOf( entityManagerPrimary.createNativeQuery(" select FUN_SEQ('TRX_NO_SEQ')").getSingleResult());
        vo.setTrxNo(trxNo);

        this.bulidTradePayOrderVo(vo);

        return param;
    }

    @Transactional(rollbackFor = Exception.class)
    public void bulidTradePayOrderVo(TradePaymentOrderVo vo) {

        TradePaymentOrderEntity tradeOrder = tradePaymentOrderRepository.getByMerchantNoAndMerchantOrderNo(vo.getMerchantNo(), vo.getMerchantOrderNo());

        if (tradeOrder == null) {

            TradePaymentOrderEntity tradePaymentOrderEntity = mapper.map(vo, TradePaymentOrderEntity.class);

            tradePaymentOrderRepository.save(tradePaymentOrderEntity);

        } else {

            // 订单存在
            if (tradeOrder.getOrderAmount().compareTo(vo.getOrderAmount()) != 0) {
                throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR, "错误的订单");
            }

            if (TradeStatusEnum.SUCCESS.name().equals(tradeOrder.getStatus())) {
                throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR, "订单已支付成功,无需重复支付");
            }

        }


//        return mapper.map(tradePaymentOrderEntity, TradePaymentOrderVo.class);
    }
}
