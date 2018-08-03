package com.distributed.transation.bankmessage;

import com.distributed.transation.bankmessage.biz.BankMessageService;

import java.util.Map;

/**
 * 银行消息处理线程
 *
 * @author ssk www.distributed.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-03-上午 9:44
 */
public class BankMessageTask implements Runnable {

    private BankMessageService bankMessageService;

    private Map<String, String> bankMessageMap;


    public BankMessageTask(Map<String, String> bankMessageMap) {

        this.bankMessageMap = bankMessageMap;
    }

    @Override
    public void run() {

        bankMessageService.completePay(bankMessageMap);

    }

    public BankMessageService getBankMessageService() {

        return bankMessageService;
    }

    public void setBankMessageService(BankMessageService bankMessageService) {

        this.bankMessageService = bankMessageService;
    }


}
