package com.distributed.transaction.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
        entityManagerFactoryRef = "entityManagerFactoryProduct",
        transactionManagerRef = "transactionManagerProduct",
        basePackages = {"com.distributed.transaction.module.product.repository","com.distributed.transaction.module.user.repository"})
public class ProductDataBaseConfig {

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    @Qualifier("productDataSource")
    private DataSource productDataSource;

    /**
     * 本地查询或者取得序列号执行存储过程使用
     * @param builder
     * @return
     */
    @Bean(name = "entityManagerProduct")
    public EntityManager entityManagerProduct(EntityManagerFactoryBuilder builder) {

        return entityManagerFactoryProduct(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactoryProduct")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryProduct(EntityManagerFactoryBuilder builder) {

        return builder
                .dataSource(productDataSource)
                .properties(getVendorProperties(productDataSource))
                .packages("com.distributed.transaction.module.user.domain","com.distributed.transaction.module.product.domain")
                .persistenceUnit("productPersistenceUnit")
                .build();
    }

    private Map<String, String> getVendorProperties(DataSource dataSource) {

        return jpaProperties.getHibernateProperties(dataSource);
    }

    @Bean(name = "transactionManagerProduct")
    public PlatformTransactionManager transactionManagerProduct(EntityManagerFactoryBuilder builder) {

        return new JpaTransactionManager(entityManagerFactoryProduct(builder).getObject());
    }




}
