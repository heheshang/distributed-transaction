package com.distributed.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author ssk
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = { "com.distributed.transaction.trade" })
//@EnableJpaRepositories(basePackages = "com.hnapay.fcs.gateway.repository")
@EntityScan(basePackages = "com.distributed.transaction.trade.domain")
public class DistributedTradeApplication {

    public static void main(String[] args) {

        SpringApplication.run(DistributedTradeApplication.class, args);
    }
}
