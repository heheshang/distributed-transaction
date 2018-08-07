package com.distributed.transaction.service.banknotify;

import com.distributed.transaction.enums.PayWayEnum;
import com.distributed.transaction.enums.trade.TradeStatusEnum;
import com.distributed.transaction.exception.TradeBizException;
import com.distributed.transaction.module.message.domain.TransactionMessageEntity;
import com.distributed.transaction.module.message.repository.TransactionMessageRepository;
import com.distributed.transaction.module.message.vo.TransactionMessage;
import com.distributed.transaction.module.trade.domain.TradePaymentRecordEntity;
import com.distributed.transaction.module.trade.repository.TradePaymentRecordRepository;
import com.distributed.transaction.module.trade.vo.TradePaymentRecord;
import com.distributed.transaction.service.ITranService;
import com.distributed.transaction.service.message.AccountingTransactionMessageServiceImpl;
import com.distributed.transaction.service.trade.TradePaymentBizServiceImpl;
import com.distributed.transaction.trade.api.banknotify.BankNotifyMessage;
import com.distributed.transaction.trade.api.banknotify.BankNotifyParam;
import com.distributed.transaction.utils.DateUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-25-下午 3:04
 */
@Component
@Log4j2
public class BankNotifyTestServiceImpl implements ITranService<BankNotifyParam, BankNotifyMessage> {

    @Autowired
    private DozerBeanMapper mapper;

    @Autowired
    private TradePaymentRecordRepository tradePaymentRecordRepository;

    @Autowired
    private AccountingTransactionMessageServiceImpl transactionMessageService;

    @Autowired
    private TransactionMessageRepository transactionMessageRepository;

    @Autowired
    private TradePaymentBizServiceImpl tradePaymentBizService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BankNotifyMessage handle(BankNotifyParam bankNotifyParam) {

        log.info("接收到queue服务支付结果通知 [{}]", bankNotifyParam.toString());

        String bankNo = bankNotifyParam.getNotifyMap().get("out_trade_no");
        String trxNo = bankNotifyParam.getNotifyMap().get("trxNo");
        String payWayCode = bankNotifyParam.getNotifyMap().get("payWayCode");
        String resultCode = bankNotifyParam.getNotifyMap().get("result_code");


        log.info("------[接收到要处理的银行订单{}],支付记录订单[{}]--------[开始处理时间{}]------", bankNo, trxNo, DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));

        //根据银行订单号和支付交易记录流水号获取交易记录信息
        TradePaymentRecordEntity tradeRecordEntity = tradePaymentRecordRepository.getByBankOrderNoAndTrxNo(bankNo, trxNo);


        if (tradeRecordEntity == null) {
            log.error("非法订单，订单{}不存在支付流水号:{}", bankNo, trxNo);
            throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR, ",非法订单,订单不存在");
        }

        // 幂等判断
        if (TradeStatusEnum.SUCCESS.name().equals(tradeRecordEntity.getStatus())) {
            log.info("订单{}为成功状态,不做业务处理", bankNo);
            return new BankNotifyMessage();
        }

        // 判断交易状态是否为等待支付 状态
        if (!TradeStatusEnum.WAITING_PAYMENT.name().equals(tradeRecordEntity.getStatus())) {
            log.info("订单{}状态为非等待支付状态,不做业务处理", bankNo);
            return new BankNotifyMessage();
        }

        // 银行返回订单是否为支付成功状态
        boolean orderIsSuccess = false;
        //银行流水号
        String bankTrxNo = "";
        //订单完成时间
        Date timeEnd = null;
        //银行返回信息
        String bankReturnMsg = "";

        PayWayEnum payWayEnum = PayWayEnum.getEnum(payWayCode);

        if (payWayEnum == null) {
            throw new TradeBizException(TradeBizException.TRADE_PAY_WAY_ERROR, "错误的支付方式");
        }

        switch (payWayEnum) {
            case TEST_PAY_HTTP_CLIENT:
                if (TradeStatusEnum.SUCCESS.name().equals(resultCode)) {

                    String timeEndStr = bankNotifyParam.getNotifyMap().get("time_end");

                    if (!StringUtils.isEmpty(timeEndStr)) {
                        //订单支付完成时间
                        timeEnd = DateUtils.getDateFromString(timeEndStr, "yyyyMMddHHmmss");
                    }

                    orderIsSuccess = true;
                    bankTrxNo = bankNotifyParam.getNotifyMap().get("transaction_id");
                    bankReturnMsg = bankNotifyParam.getNotifyMap().toString();
                }
                break;
            case ALIPAY:
                break;
            case WEIXIN:
                break;
            default:
                break;
        }

        if (orderIsSuccess){

            log.info("开始处理支付成功的商户订单[{}],商户号[{}],银行订单号bankNo=[{}]",tradeRecordEntity.getMerchantOrderNo(),tradeRecordEntity.getMerchantNo(),bankNo);

            TradePaymentRecord tradePaymentRecord = mapper.map(tradeRecordEntity,TradePaymentRecord.class);

            //封装会计队列消息
            TransactionMessage transactionMessage = transactionMessageService.sealTransactionMessage(tradePaymentRecord);

            TransactionMessageEntity messageEntity = mapper.map(transactionMessage, TransactionMessageEntity.class);

            //保存消息数据
            transactionMessageRepository.save(messageEntity);

            log.info("保存消息数据 支付成功的商户订单[{}],商户号[{}],银行订单号bankNo=[{}]",tradeRecordEntity.getMerchantOrderNo(),tradeRecordEntity.getMerchantNo(),bankNo);

            //调用支付成功处理方法

            tradePaymentBizService.completeSuccessOrder(tradePaymentRecord,bankTrxNo,bankReturnMsg);
            //调用消息微服务==>发送消息


        }else {


        }

        return null;
    }


}
