package com.distributed.transation.bankmessage.biz;

import com.distributed.transaction.module.message.repository.TransactionMessageRepository;
import com.distributed.transaction.trade.BaseTradeRechargeTransApi;
import com.distributed.transaction.trade.api.TradeReq;
import com.distributed.transaction.trade.api.banknotify.BankNotifyParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 银行通知异步处理业务逻辑
 *
 * @author ssk www.distributed.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-03-上午 10:40
 */
@Component
@Log4j2
public class BankMessageService {

    @Autowired
    private BaseTradeRechargeTransApi tradeRechargeTransApi;

    @Autowired
    private TransactionMessageRepository transactionMessageRepository;

    /**
     * notifyMap.put("result_code", "SUCCESS");
     * String timeEnd = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
     * notifyMap.put("time_end", timeEnd);
     * notifyMap.put("out_trade_no", bankOrderNo);
     * notifyMap.put("transaction_id", timeEnd);
     * notifyMap.put("payWayCode", "TEST_PAY_HTTP_CLIENT");
     *
     * @param bankNotifyMap
     */
    @Transactional(rollbackFor = Exception.class)
    public void completePay(Map<String, String> bankNotifyMap) {

        String messageId = bankNotifyMap.get("messageId");

        String payTypeCode = bankNotifyMap.get("payTypeCode");

        TradeReq tradeReq = new TradeReq();

        BankNotifyParam bankNotify = new BankNotifyParam();

        bankNotify.setPayWayCode(payTypeCode);

        bankNotify.setPayTypeCode(payTypeCode);

        bankNotify.getNotifyMap().putAll(bankNotifyMap);

        tradeReq.setParams(bankNotify);
        log.info("调用 trade 服务完成交易,tradeReq=【{}】",tradeReq);
        //调用 trade 服务完成交易
        tradeRechargeTransApi.bankMessageHandle(tradeReq);
        //删除消息日志信息
        log.info("删除消息日志信息,messageId=【{}】",messageId);
        transactionMessageRepository.deleteByMessageId(messageId);

    }

}
