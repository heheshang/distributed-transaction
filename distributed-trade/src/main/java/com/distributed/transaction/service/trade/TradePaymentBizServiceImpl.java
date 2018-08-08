package com.distributed.transaction.service.trade;

import com.distributed.transaction.module.trade.vo.TradePaymentRecord;
import lombok.extern.log4j.Log4j2;
import org.mengyun.tcctransaction.api.Compensable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-07-上午 9:08
 */
@Component
@Log4j2
public class TradePaymentBizServiceImpl {

    @Compensable(confirmMethod = "confirmCompleteSuccessOrder", cancelMethod = "cancelCompleteSuccessOrder")
    public void completeSuccessOrder(TradePaymentRecord tradePaymentRecord, String bankTrxNo, Date timeEnd, String bankReturnMsg) {

    }

    @Transactional(rollbackFor = Exception.class)
    public void confirmCompleteSuccessOrder(TradePaymentRecord tradePaymentRecord, String bankTrxNo, String bankReturnMsg) {

    }

    @Transactional(rollbackFor = Exception.class)
    public void cancelCompleteSuccessOrder(TradePaymentRecord tradePaymentRecord, String bankTrxNo, String bankReturnMsg) {

    }
}
