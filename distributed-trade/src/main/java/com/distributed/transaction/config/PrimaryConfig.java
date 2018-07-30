package com.distributed.transaction.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;


/**
 * 将数据源注入到 实体管理器工厂，配置 base、domian 的位置
 * <span color="red"> 由于事务管理器中,及数据源配置中已经配置了 连接信息,
 * 不需要在继承主从切换组件进行数据源切换,jpa 配置的连接工厂会找到相应的数据库连接信息</span>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @date 2018/07/19 下午 1:46
 * @since v1.0
 **/
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryPrimary",//配置连接工厂 entityManagerFactory
        transactionManagerRef = "transactionManagerPrimary", //配置 事物管理器  transactionManager
        basePackages = {"com.distributed.transaction.module.trade.repository"}//设置dao（repo）所在位置
)
public class PrimaryConfig {

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    @Qualifier("primaryDataSource")
    private DataSource primaryDataSource;


    /**
     * @param builder
     * @return
     */
    @Bean(name = "entityManagerFactoryPrimary")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder builder) {

        return builder
                //设置数据源
                .dataSource(primaryDataSource)
                //设置数据源属性
                .properties(getVendorProperties(primaryDataSource))
                //设置实体类所在位置.扫描所有带有 @Entity 注解的类
                .packages("com.distributed.transaction.module.trade.domain")
                // Spring会将EntityManagerFactory注入到Repository之中.有了 EntityManagerFactory之后,
                // Repository就能用它来创建 EntityManager 了,然后Entity就可以针对数据库执行操作
                .persistenceUnit("primaryPersistenceUnit")

                .build();

    }

    /**
     * 事物管理器
     *
     * @param builder
     * @return
     */
    @Bean(name = "transactionManagerPrimary")
    @Primary
    PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {

        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
    }

    private Map<String, String> getVendorProperties(DataSource dataSource) {

        return jpaProperties.getHibernateProperties(dataSource);
    }


}
