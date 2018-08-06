package com.distributed.transaction.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-30-下午 3:28
 */
@Configuration
public class JpaDataSourceConfig {

    @Bean(name = "accountPrimaryDataSource")
    @Primary
    @Qualifier("accountPrimaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.account")
    public DataSource accountPrimaryDataSource() {

        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource() {

        return DataSourceBuilder.create().build();
    }

}
