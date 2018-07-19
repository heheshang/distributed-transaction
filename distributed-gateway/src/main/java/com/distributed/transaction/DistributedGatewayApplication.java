package com.distributed.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
@EnableAsync
@EnableScheduling
//@EnableAutoConfiguration
@EnableFeignClients(basePackages = {"com.distributed.transaction.gateway"})
public class DistributedGatewayApplication {

    public static void main(String[] args) {

        SpringApplication.run(DistributedGatewayApplication.class, args);
    }
}
