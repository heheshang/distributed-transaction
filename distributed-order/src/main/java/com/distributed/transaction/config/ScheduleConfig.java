package com.distributed.transaction.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author jinsheng
 * @since 2016-08-11 15:41
 */
@Configuration
@EnableScheduling
//@ConditionalOnProperty("scheduled.on")
public class ScheduleConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(scheduledTaskExecutor());
    }

    @Bean(destroyMethod="shutdown")
    public Executor scheduledTaskExecutor() {
        return Executors.newScheduledThreadPool(20);
    }
}
