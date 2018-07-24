package com.distributed.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;


@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.distributed.transaction"})
@EnableFeignClients(basePackages = {"com.distributed.transaction.gateway"})
@EnableScheduling
public class DistributedShopApplication {

    public static void main(String[] args) {

        SpringApplication.run(DistributedShopApplication.class, args);
    }
}
