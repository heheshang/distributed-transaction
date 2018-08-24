package com.distributed.transaction;

import com.distributed.transaction.config.HttpClientConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
@EnableFeignClients(basePackages = {"com.distributed.transaction.gateway","com.distributed.transaction.trade"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Log4j2
@ImportAutoConfiguration(classes = HttpClientConfig.class)
public class DistributedGatewayApplication {

    public static void main(String[] args) {

        SpringApplication.run(DistributedGatewayApplication.class, args);
        log.info("启动完成");
        log.error("启动完成");
        log.debug("启动完成");
        log.trace("启动完成");
        log.warn("启动完成");
    }
}
