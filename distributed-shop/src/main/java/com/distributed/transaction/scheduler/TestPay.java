package com.distributed.transaction.scheduler;

import com.distributed.transaction.key.MerchantKey;
import com.distributed.transaction.service.TranService;
import com.google.common.collect.Lists;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-23-下午 2:51
 */
@Component
@Log4j2
public class TestPay {

    @Autowired
    private TranService service;


    private static List<MerchantKey> merchantKeys = Lists.newArrayList(
            new MerchantKey("4c52295065654407b42797cda80dd07d", "6606353e0dab4f7b9a723f2d3e3276b7"),
            new MerchantKey("abcf900288114d5fa7fde764966eb2ff", "d94d7c2d56eb4b06828cf746c8be17e6"),
            new MerchantKey("8ba459f55ff04f39b0128a3cb4a48f2b", "3e2ca2eb1f024570b241d65eb4832c37"),
            new MerchantKey("93d1ea2f9f3b448994f39d6efc7746ef", "fad7ea79810c4af7a973c091aa7c6143"),
            new MerchantKey("ca6577dff6d647ac882dfb405ceda21e", "1b8da6c9b7544856955fcff6bf920f84")

    );

    private static LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();

    @Scheduled(cron = "0 0/1 4-23 * * ?")
    protected void run() {

        ExecutorService threadPool = new ThreadPoolExecutor(
                5,
                10,
                30000L,
                TimeUnit.MICROSECONDS,
                queue, new NamedThreadFactory("Test-Pay-POOL"));



        CountDownLatch latch = new CountDownLatch(5);
        try {
            log.info("测试充值开始");


            for (MerchantKey merchantKey : merchantKeys) {
                final String payKey = merchantKey.getPayKey();
                final String paySecret = merchantKey.getPaySecret();
                threadPool.submit(new TestPayThread(payKey, paySecret, latch, service));

            }

            latch.await();
            threadPool.shutdown();
            log.info("测试充值结束");
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            threadPool.shutdown();
        }
    }


}
