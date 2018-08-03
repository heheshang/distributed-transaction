package com.distributed.transation.runner;

import com.distributed.transation.scheduled.ITransactionMessageScheduled;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * @author ssk www.distributed.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-02-下午 5:10
 */
@Component
@Order(1)
@Log4j2
public class ScheduledRunner implements ApplicationRunner {

    @Autowired
    private ThreadPoolTaskExecutor threadPool;

    @Autowired
    private ITransactionMessageScheduled transactionMessageScheduled;

    @Override
    public void run(ApplicationArguments args) {

        log.info("启动完成,运行超时清理任务");

        try {

            threadPool.execute(() -> {
                for (; ; ) {

                    log.info("执行(处理[WAITING_CONFIRM]状态的消息)任务开始");
                    transactionMessageScheduled.handleWaitingConfirmTimeOutMessages();
                    log.info("执行(处理[WAITING_CONFIRM]状态的消息)任务结束");

                    try {
                        log.info("[WAITING_CONFIRM]睡眠60秒");
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                    }
                }
            });

            threadPool.execute(() -> {
                for (; ; ) {

                    log.info("执行(处理[SENDING]的消息)任务开始");
                    transactionMessageScheduled.handleSendingTimeOutMessages();
                    log.info("执行(处理[SENDING]的消息)任务结束");
                    try {
                        log.info("[SENDING]睡眠60秒");
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                    }

                }
            });

        } catch (Exception e) {

            log.error("启动完成,运行超时清理任务异常啦,  error[{}]", e);
        }
    }
}
