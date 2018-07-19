package com.distributed.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author ssk
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = { "com.distributed.transaction.api" })
//@EnableJpaRepositories(basePackages = "com.hnapay.fcs.gateway.repository")
@EntityScan(basePackages = "com.hnapay.fcs.gateway.entity")
public class DistributedOrderApplication {

    public static void main(String[] args) {

        SpringApplication.run(DistributedOrderApplication.class, args);
    }
}
