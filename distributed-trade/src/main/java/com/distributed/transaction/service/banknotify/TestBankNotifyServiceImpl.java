package com.distributed.transaction.service.banknotify;

import com.distributed.transaction.service.ITranService;
import com.distributed.transaction.trade.api.banknotify.BankNotifyMessage;
import com.distributed.transaction.trade.api.banknotify.BankNotifyParam;
import lombok.extern.log4j.Log4j2;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-25-下午 3:04
 */
@Component
@Log4j2
public class TestBankNotifyServiceImpl implements ITranService<BankNotifyParam, BankNotifyMessage> {

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public BankNotifyMessage handle(BankNotifyParam bankNotifyParam) {

        return null;
    }



}
