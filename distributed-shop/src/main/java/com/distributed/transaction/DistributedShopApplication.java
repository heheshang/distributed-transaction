package com.distributed.transaction;

import com.distributed.transaction.config.HttpClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
* 模拟商城
* @author ssk www.8win.com Inc.All rights reserved
* @date 2018/08/09 下午 3:24
* @since v1.0
**/
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.distributed.transaction"})
@EnableFeignClients(basePackages = {"com.distributed.transaction.gateway"})
@EnableScheduling
@ImportAutoConfiguration(classes = HttpClientConfig.class)
public class DistributedShopApplication {

    public static void main(String[] args) {

        SpringApplication.run(DistributedShopApplication.class, args);
    }
}
