package com.distributed.transation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
/**
*
* @author ssk www.distributed.com Inc.All rights reserved
* @date 2018/08/03 下午 1:49
* @since v1.0
**/
@SpringBootApplication
@EnableScheduling
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.distributed.transaction.trade"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DistributedQueueApplication {



    public static void main(String[] args) {

        SpringApplication.run(DistributedQueueApplication.class, args);
    }
}
