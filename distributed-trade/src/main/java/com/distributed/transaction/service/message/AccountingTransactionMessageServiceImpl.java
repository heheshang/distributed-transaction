package com.distributed.transaction.service.message;

import com.distributed.transaction.enums.message.AreadlyDeadEnum;
import com.distributed.transaction.enums.message.MessageStatusEnum;
import com.distributed.transaction.enums.message.MsgDataTypeEnum;
import com.distributed.transaction.enums.message.NotifyDestinationNameEnum;
import com.distributed.transaction.module.accounting.vo.AccountingVoucher;
import com.distributed.transaction.module.message.vo.TransactionMessage;
import com.distributed.transaction.module.trade.vo.TradePaymentRecord;
import com.distributed.transaction.utils.SingletonGsonEnum;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-06-下午 2:49
 */
@Component
@Log4j2
public class AccountingTransactionMessageServiceImpl {

    /**
     * 封装会计凭证消息信息
     *
     * @param paymentRecord 支付记录信息
     * @return
     */
    public TransactionMessage sealTransactionMessage(TradePaymentRecord paymentRecord) {

        log.info("封装会计凭证消息信息开始商户订单号=[{}],交易流水号=[{}]", paymentRecord.getMerchantOrderNo(), paymentRecord.getTrxNo());

        AccountingVoucher voucher = new AccountingVoucher();

        //交易类型
        voucher.setEntryType((short) 1);
        //付款方变动金额
        voucher.setPayerChangeAmount(BigDecimal.ZERO);
        //平台交易流水号
        voucher.setVoucherNo(paymentRecord.getTrxNo());
        //付款方账户
        voucher.setPayerAccountNo("");
        //系统来源
        voucher.setFromSystem((short) 1);
        //账户类型
        voucher.setPayerAccountType((short) 1);
        //付款方手续费
        voucher.setPayerFee(BigDecimal.ZERO);
        //银行订单金额
        voucher.setBankChangeAmount(paymentRecord.getOrderAmount());
        voucher.setReceiverChangeAmount(paymentRecord.getOrderAmount());
        voucher.setReceiverAccountNo(paymentRecord.getMerchantNo());
        voucher.setBankAccount("");
        voucher.setBankChannelCode(paymentRecord.getPayWayCode());
        voucher.setProfit(paymentRecord.getPlatProfit());
        voucher.setIncome(paymentRecord.getPlatIncome());
        voucher.setCost(paymentRecord.getPlatCost());
        voucher.setBankOrderNo(paymentRecord.getBankOrderNo());
        voucher.setPayAmount(BigDecimal.ZERO);
        voucher.setReceiverAccountType((short) 1);
        voucher.setReceiverFee(paymentRecord.getReceiverFee() == null ? BigDecimal.ZERO : paymentRecord.getReceiverFee());
        voucher.setRemark("模拟支付信息");
        voucher.setMessageId(UUID.randomUUID().toString().replace("-", ""));

        String messageBody = SingletonGsonEnum.instences.getGson().toJson(voucher);

        TransactionMessage message = new TransactionMessage();

        message.setMessageId(voucher.getMessageId());
        message.setMessageBody(messageBody);
        message.setMsgDataType( MsgDataTypeEnum.JSON);
        message.setConsumerQueue(NotifyDestinationNameEnum.ACCOUNTING_NOTIFY);
        message.setMessageSendTimes(0);
        message.setAreadlyDead(AreadlyDeadEnum.NO);
        message.setStatus(MessageStatusEnum.WAITING_CONFIRM);
        message.setRemark("");
        message.setField1(paymentRecord.getBankOrderNo());
        message.setField2("");
        message.setField3("");

        return message;
    }




}
