package com.distributed.transaction.interceptor;

import com.distributed.transaction.BaseParam;
import com.distributed.transaction.enums.FundInfoTypeEnum;
import com.distributed.transaction.enums.PayTypeEnum;
import com.distributed.transaction.enums.PayWayEnum;
import com.distributed.transaction.enums.TrxTypeEnum;
import com.distributed.transaction.enums.trade.OrderFromEnum;
import com.distributed.transaction.enums.trade.TradeStatusEnum;
import com.distributed.transaction.exception.DistributedException;
import com.distributed.transaction.exception.TradeBizException;
import com.distributed.transaction.module.product.domain.TccPayWayEntity;
import com.distributed.transaction.module.product.repository.TccPayWayRepository;
import com.distributed.transaction.module.product.vo.TccPayWay;
import com.distributed.transaction.module.trade.domain.TradePaymentOrderEntity;
import com.distributed.transaction.module.trade.domain.TradePaymentRecordEntity;
import com.distributed.transaction.module.trade.repository.TradePaymentOrderRepository;
import com.distributed.transaction.module.trade.repository.TradePaymentRecordRepository;
import com.distributed.transaction.module.trade.vo.TradePaymentOrder;
import com.distributed.transaction.module.trade.vo.TradePaymentRecord;
import com.distributed.transaction.module.user.domain.TccUserInfoEntity;
import com.distributed.transaction.module.user.domain.TccUserPayConfigEntity;
import com.distributed.transaction.module.user.repository.TccUserInfoRepository;
import com.distributed.transaction.module.user.repository.TccUserPayConfigRepository;
import com.distributed.transaction.trade.api.recharge.RechargeParam;
import lombok.extern.log4j.Log4j2;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

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
    public RechargeParam bulidTradePaymentOrder(P p) throws TradeBizException {

        RechargeParam param = (RechargeParam) p;

        TccUserPayConfigEntity userPayConfig = tccUserPayConfigRepository.getByPayKey(param.getPayKey());

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

        TccPayWay payWay = mapper.map(tccPayWay, TccPayWay.class);


        TradePaymentOrder vo = mapper.map(param, TradePaymentOrder.class);

        vo.setPayWayCode(tccPayWay.getPayWayCode());
        vo.setFundIntoType(userPayConfig.getFundIntoType());
        vo.setStatus(TradeStatusEnum.WAITING_PAYMENT.name());
        vo.setMerchantNo(userInfoEntity.getUserNo());
        vo.setMerchantName(userInfoEntity.getUserName());

        TradePaymentOrder paymentOrder = this.bulidTradePayOrderVo(vo);

        TradePaymentRecord paymentRecord = this.sealTccTradePaymentRecord(paymentOrder, payWay);

        param.setBankOrderNo(paymentRecord.getBankOrderNo());

        param.setTrxNo(paymentRecord.getTrxNo());

        param.setStatus(paymentRecord.getStatus());

        param.setMerchantNo(paymentOrder.getMerchantNo());

        param.setMerchantOrderNo(paymentOrder.getMerchantOrderNo());


        return param;
    }

    @Transactional(rollbackFor = Exception.class)
    public TradePaymentOrder bulidTradePayOrderVo(TradePaymentOrder paymentOrder) {

        TradePaymentOrderEntity tradeOrder = tradePaymentOrderRepository.getByMerchantNoAndMerchantOrderNo(paymentOrder.getMerchantNo(), paymentOrder.getMerchantOrderNo());

        if (tradeOrder == null) {

            paymentOrder.setPayTypeName(PayTypeEnum.getEnum(paymentOrder.getPayTypeCode()) == null ? "" : PayTypeEnum.getEnum(paymentOrder.getPayTypeCode()).getDesc());

            paymentOrder.setPayWayName(PayWayEnum.getEnum(paymentOrder.getPayWayCode()) == null ? "" : PayWayEnum.getEnum(paymentOrder.getPayWayCode()).getDesc());

            TradePaymentOrderEntity tradePaymentOrderEntity = mapper.map(paymentOrder, TradePaymentOrderEntity.class);


            tradeOrder = tradePaymentOrderRepository.save(tradePaymentOrderEntity);

        } else {

            // 订单存在
            if (tradeOrder.getOrderAmount().compareTo(paymentOrder.getOrderAmount()) != 0) {
                throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR, "错误的订单");
            }

            if (TradeStatusEnum.SUCCESS.name().equals(tradeOrder.getStatus())) {
                throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR, "订单已支付成功,无需重复支付");
            }

            tradeOrder.setPayTypeCode(PayTypeEnum.getEnum(paymentOrder.getPayTypeCode()) == null ? "" : PayTypeEnum.getEnum(paymentOrder.getPayTypeCode()).getWay());
            tradeOrder.setPayTypeName(PayTypeEnum.getEnum(paymentOrder.getPayTypeCode()) == null ? "" : PayTypeEnum.getEnum(paymentOrder.getPayTypeCode()).getDesc());
            tradeOrder.setPayWayCode(PayWayEnum.getEnum(paymentOrder.getPayWayCode()) == null ? "" : PayWayEnum.getEnum(paymentOrder.getPayWayCode()).name());
            tradeOrder.setPayWayName(PayWayEnum.getEnum(paymentOrder.getPayWayCode()) == null ? "" : PayWayEnum.getEnum(paymentOrder.getPayWayCode()).getDesc());

            tradeOrder = tradePaymentOrderRepository.save(tradeOrder);
        }


        return mapper.map(tradeOrder, TradePaymentOrder.class);
    }

    @Transactional(rollbackFor = Exception.class)
    public TradePaymentRecord sealTccTradePaymentRecord(TradePaymentOrder vo, TccPayWay payWay) {

        TradePaymentRecord recordVo = mapper.map(vo, TradePaymentRecord.class);

        String trxNo = String.valueOf(entityManagerPrimary.createNativeQuery(" select FUN_SEQ('TRX_NO_SEQ')").getSingleResult());

        recordVo.setTrxNo(trxNo);

        PayWayEnum payWayEnum = PayWayEnum.getEnum(vo.getPayWayCode());

        if (payWayEnum == null) {
            throw new TradeBizException(TradeBizException.TRADE_PARAM_ERROR, "订单交易类型错误");
        }

        switch (payWayEnum) {
            case TEST_PAY_HTTP_CLIENT:
                recordVo.setBankOrderNo(trxNo);
                recordVo.setBankReturnMsg("模拟支付");
                break;
            default:
                String bankOrderNo = String.valueOf(entityManagerPrimary.createNativeQuery(" select FUN_SEQ('BANK_ORDER_NO_SEQ')").getSingleResult());
                recordVo.setBankOrderNo(bankOrderNo);

                break;
        }

        FundInfoTypeEnum fundInfoType = FundInfoTypeEnum.getEnum(vo.getFundIntoType());

        if (fundInfoType != null) {

            switch (fundInfoType) {
                case PLAT_RECEIVES:
                    //订单金额
                    BigDecimal orderAmount = vo.getOrderAmount();
                    //平台收入 = 订单金额 * 支付费率(设置的费率除以100为真实费率)
                    BigDecimal feeRate = new BigDecimal(payWay.getPayRate());
                    BigDecimal platIncome = orderAmount.multiply(feeRate).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);
                    //平台成本 = 订单金额 * 微信费率(设置的费率除以100为真实费率)

                    BigDecimal platRate = new BigDecimal("0.6");
                    BigDecimal platCost = orderAmount.multiply(platRate).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);
                    //平台利润 = 平台收入 - 平台成本
                    BigDecimal platProfit = platIncome.subtract(platCost);

                    //费率
                    recordVo.setFeeRate(feeRate);
                    //平台成本
                    recordVo.setPlatCost(platCost);
                    //平台收入
                    recordVo.setPlatIncome(platIncome);
                    //平台利润
                    recordVo.setPlatProfit(platProfit);

                    break;
                default:
                    break;
            }
        }
        recordVo.setPayerPayAmount(vo.getOrderAmount());
        //交易类型
        recordVo.setTrxType(TrxTypeEnum.EXPENSE.name());
        //订单来源
        recordVo.setOrderFrom(OrderFromEnum.USER_EXPENSE.name());
        //订单状态
        recordVo.setStatus(TradeStatusEnum.WAITING_PAYMENT.name());

        TradePaymentRecordEntity entity = tradePaymentRecordRepository.save(mapper.map(recordVo, TradePaymentRecordEntity.class));
        log.info("sealTccTradePaymentRecord 封装交易记录结束");

        return mapper.map(entity, TradePaymentRecord.class);
    }
}
