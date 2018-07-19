package com.distributed.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DistributedRegistryApplication {

    public static void main(String[] args) {

        SpringApplication.run(DistributedRegistryApplication.class, args);
    }
}
