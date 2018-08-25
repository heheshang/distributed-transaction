package com.distributed.transaction.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * <span color="red"> 由于事务管理器中,及数据源配置中已经配置了 连接信息,
 * * 不需要在继承主从切换组件进行数据源切换,jpa 配置的连接工厂会找到相应的数据库连接信息</span>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @date 2018/07/19 下午 1:47
 * @since v1.0
 **/
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactorySecondary",
        transactionManagerRef = "transactionManagerSecondary",
        basePackages = {"com.distributed.transaction.module.message.repository"})
public class SecondaryConfig {

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    @Qualifier("secondaryDataSource")
    private DataSource secondaryDataSource;

    /**
     * 本地查询或者取得序列号执行存储过程使用
     *
     * @param builder
     * @return
     */
    @Bean(name = "entityManagerSecondary")
    public EntityManager entityManagerSecondary(EntityManagerFactoryBuilder builder) {

        return entityManagerFactorySecondary(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactorySecondary")
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary(EntityManagerFactoryBuilder builder) {

        return builder
                .dataSource(secondaryDataSource)
                .properties(getVendorProperties())
                .packages("com.distributed.transaction.module.message.domain")
                .persistenceUnit("secondaryPersistenceUnit")
                .build();
    }

    private Map<String, Object> getVendorProperties() {

        return jpaProperties.getHibernateProperties(new HibernateSettings());
    }

    @Bean(name = "transactionManagerSecondary")
    public PlatformTransactionManager transactionManagerSecondary(EntityManagerFactoryBuilder builder) {

        return new JpaTransactionManager(entityManagerFactorySecondary(builder).getObject());
    }


}
