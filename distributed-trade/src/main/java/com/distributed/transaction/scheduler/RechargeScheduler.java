package com.distributed.transaction.scheduler;

import com.distributed.transaction.register.TranServiceComponentRegister;
import com.distributed.transaction.register.TransTypeEnum;
import com.distributed.transaction.service.ITranService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-04-下午 2:30
 */
@Component
@Log4j2
public class RechargeScheduler {


    @Scheduled(cron = "0 0/1 4-23 * * ?")
    protected void run() {
        ITranService service = TranServiceComponentRegister.getTransMessage(TransTypeEnum.TEST_RECHARGE_PAY);
        log.info("定时任务充值测试");
        service.process();
    }
}
