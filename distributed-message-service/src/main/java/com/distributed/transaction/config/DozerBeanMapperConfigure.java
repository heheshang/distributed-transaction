package com.distributed.transaction.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 实体转换器配置
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-06-下午 4:55
 */
@Configuration
public class DozerBeanMapperConfigure {

    @Bean
    public DozerBeanMapper mapper() {

        DozerBeanMapper mapper = new DozerBeanMapper();
        return mapper;
    }
}
