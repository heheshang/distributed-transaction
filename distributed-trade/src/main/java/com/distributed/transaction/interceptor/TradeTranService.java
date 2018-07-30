package com.distributed.transaction.interceptor;
import java.math.BigDecimal;
import java.util.Date;

import com.distributed.transaction.BaseParam;
import com.distributed.transaction.module.trade.domain.TradePaymentRecordEntity;
import com.distributed.transaction.module.trade.repository.TradePaymentOrderRepository;
import com.distributed.transaction.module.trade.repository.TradePaymentRecordRepository;
import com.distributed.transaction.module.user.domain.TccUserPayConfigEntity;
import com.distributed.transaction.module.user.repository.TccUserPayConfigRepository;
import com.distributed.transaction.trade.api.recharge.RechargeParam;
import lombok.extern.log4j.Log4j2;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

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


    public RechargeParam bulidTradePaymentOrder(P p) {

        RechargeParam param = (RechargeParam) p;

        TccUserPayConfigEntity userPayConfig = tccUserPayConfigRepository.getByPayKey(p.getPayKey());

        userPayConfig.setEditTime(new Timestamp(System.currentTimeMillis()));
        log.info("获取用户配置信息为[{}]", userPayConfig.getUserNo());


        TradePaymentRecordEntity tradePaymentRecordEntity = new TradePaymentRecordEntity();
//        tradePaymentRecordEntity.setId("");
//        tradePaymentRecordEntity.setVersion(0);
        tradePaymentRecordEntity.setCreateTime(new Date());
        tradePaymentRecordEntity.setStatus("12222");
        tradePaymentRecordEntity.setEditor("22222");
        tradePaymentRecordEntity.setCreater("11111");
        tradePaymentRecordEntity.setEditTime(new Date());
        tradePaymentRecordEntity.setProductName("22222");
        tradePaymentRecordEntity.setMerchantOrderNo("11111");
        tradePaymentRecordEntity.setTrxNo("22222");
        tradePaymentRecordEntity.setBankOrderNo("222");
        tradePaymentRecordEntity.setBankTrxNo("1111");
        tradePaymentRecordEntity.setMerchantName("ceshi");
        tradePaymentRecordEntity.setMerchantNo("saaaa");
        tradePaymentRecordEntity.setPayerUserNo("2222");
        tradePaymentRecordEntity.setPayerName("cess");
        tradePaymentRecordEntity.setPayerPayAmount(new BigDecimal("0"));
        tradePaymentRecordEntity.setPayerFee(new BigDecimal("0"));
        tradePaymentRecordEntity.setPayerAccountType("222");
        tradePaymentRecordEntity.setReceiverUserNo("111");
        tradePaymentRecordEntity.setReceiverName("222");
        tradePaymentRecordEntity.setReceiverPayAmount(new BigDecimal("0"));
        tradePaymentRecordEntity.setReceiverFee(new BigDecimal("0"));
        tradePaymentRecordEntity.setReceiverAccountType("222");
        tradePaymentRecordEntity.setOrderIp("127.0.0.1");
        tradePaymentRecordEntity.setOrderRefererUrl("222");
        tradePaymentRecordEntity.setOrderAmount(new BigDecimal("0"));
        tradePaymentRecordEntity.setPlatIncome(new BigDecimal("0"));
        tradePaymentRecordEntity.setFeeRate(new BigDecimal("0"));
        tradePaymentRecordEntity.setPlatCost(new BigDecimal("0"));
        tradePaymentRecordEntity.setPlatProfit(new BigDecimal("0"));
        tradePaymentRecordEntity.setReturnUrl("http://www.baidu.com");
        tradePaymentRecordEntity.setNotifyUrl("http://www.baidu.com");
        tradePaymentRecordEntity.setPayWayCode("1");
        tradePaymentRecordEntity.setPayWayName("2");
        tradePaymentRecordEntity.setPaySuccessTime(new Date());
        tradePaymentRecordEntity.setCompleteTime(new Date());
        tradePaymentRecordEntity.setIsRefund("否");
        tradePaymentRecordEntity.setRefundTimes(0);
        tradePaymentRecordEntity.setSuccessRefundAmount(new BigDecimal("0"));
        tradePaymentRecordEntity.setTrxType("");
        tradePaymentRecordEntity.setOrderFrom("");
        tradePaymentRecordEntity.setPayTypeCode("");
        tradePaymentRecordEntity.setPayTypeName("");
        tradePaymentRecordEntity.setFundIntoType("");
        tradePaymentRecordEntity.setRemark("");
        tradePaymentRecordEntity.setField1("");
        tradePaymentRecordEntity.setField2("");
        tradePaymentRecordEntity.setField3("");
        tradePaymentRecordEntity.setField4("");
        tradePaymentRecordEntity.setField5("");
        tradePaymentRecordEntity.setBankReturnMsg("222");


        tradePaymentRecordRepository.save(tradePaymentRecordEntity);
        return param;
    }
}
