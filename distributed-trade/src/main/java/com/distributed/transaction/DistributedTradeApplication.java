package com.distributed.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author ssk
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = { "com.distributed.transaction.trade" })
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan
public class DistributedTradeApplication {

    public static void main(String[] args) {

        SpringApplication.run(DistributedTradeApplication.class, args);
    }

    /**
     * 初始化配置：配置观察者
     * @return
     */
/*    @Bean(name="handlerSet")
    public Set<IMessageHandler> handlerSet() {
        Set<IMessageHandler> handlerSet = new HashSet<IMessageHandler>();
        IMessageHandler notifyMessageHandler = SpringBeanUtil.getBean("notifyMessageHandler");
        IMessageHandler emailMessageHandler = SpringBeanUtil.getBean("emailMessageHandler");

        IMessageProcessor notifyProcessor = SpringBeanUtil.getBean("notifyProcessor");
        IMessageProcessor emailProcessor = SpringBeanUtil.getBean("emailProcessor");

        // 为异步通知消息处理器注册监听者
        notifyMessageHandler.registerObserver(notifyProcessor);
        // 为邮件消息处理器注册监听者
        emailMessageHandler.registerObserver(emailProcessor);

        handlerSet.add(notifyMessageHandler);
        handlerSet.add(emailMessageHandler);

        return handlerSet;
    }*/
}
