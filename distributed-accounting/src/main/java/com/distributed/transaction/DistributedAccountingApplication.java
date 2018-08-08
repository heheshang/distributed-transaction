package com.distributed.transaction;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 会计凭证服务
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @date 2018/08/08 下午 2:52
 * @since v1.0
 **/
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.distributed.transaction.accounting"})
@EnableDiscoveryClient
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Log4j2
public class DistributedAccountingApplication {

    public static void main(String[] args) {

        SpringApplication.run(DistributedAccountingApplication.class, args);
    }
}
