package com.distributed.transaction.scheduler;

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

    @Autowired
    private ITranService tranService;

    @Scheduled(cron = "0 0/1 4-23 * * ?")
    protected void run() {

        log.info("定时任务充值测试");
//        tranService.process();
    }
}
