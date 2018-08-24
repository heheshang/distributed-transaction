package com.distributed.transation;

import com.distributed.transaction.config.HttpClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ssk www.distributed.com Inc.All rights reserved
 * @date 2018/08/03 下午 1:49
 * @since v1.0
 **/
@SpringBootApplication
@EnableScheduling
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.distributed.transaction.trade", "com.distributed.transaction.accounting"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ImportAutoConfiguration(classes = HttpClientConfig.class)
public class DistributedQueueApplication {


    public static void main(String[] args) {

        SpringApplication.run(DistributedQueueApplication.class, args);
    }


    /**
     * 根据配置获取通知间隔时间
     *
     * @return
     */
    @Bean
    public Map<Integer, Integer> sendTime() {

        Map<Integer, Integer> notifyParam = new HashMap<Integer, Integer>();
        notifyParam.put(1, 0);
        notifyParam.put(2, 1);
        notifyParam.put(3, 2);
        notifyParam.put(4, 5);
        notifyParam.put(5, 15);
        notifyParam.put(6, 20);
        return notifyParam;
    }
}
