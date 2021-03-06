package com.distributed.transaction.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
        entityManagerFactoryRef = "entityManagerFactoryAccounting",//配置连接工厂 entityManagerFactory
        transactionManagerRef = "transactionManagerAccounting", //配置 事物管理器  transactionManager
        basePackages = {"com.distributed.transaction.module.accounting.repository"}//设置dao（repo）所在位置
)
public class AccountingDataBaseConfig {

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    @Qualifier("accountingDataSource")
    private DataSource accountingDataSource;


    /**
     * @param builder
     * @return
     */
    @Bean(name = "entityManagerFactoryAccounting")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryAccounting(EntityManagerFactoryBuilder builder) {

        return builder
                //设置数据源
                .dataSource(accountingDataSource)
                //设置数据源属性
                .properties(getVendorProperties())
                //设置实体类所在位置.扫描所有带有 @Entity 注解的类
                .packages("com.distributed.transaction.module.accounting.domain")
                // Spring会将EntityManagerFactory注入到Repository之中.有了 EntityManagerFactory之后,
                // Repository就能用它来创建 EntityManager 了,然后Entity就可以针对数据库执行操作
                .persistenceUnit("accountingPrimaryPersistenceUnit")

                .build();

    }

    /**
     * 事物管理器
     *
     * @param builder
     * @return
     */
    @Bean(name = "transactionManagerAccounting")
    @Primary
    public PlatformTransactionManager transactionManagerAccounting(EntityManagerFactoryBuilder builder) {

        return new JpaTransactionManager(entityManagerFactoryAccounting(builder).getObject());
    }

    private Map<String, Object> getVendorProperties() {
        return jpaProperties.getHibernateProperties(new HibernateSettings());
    }

    /**
     * 本地查询或者取得序列号执行存储过程使用
     * @param builder
     * @return
     */
    @Bean(name = "entityManagerAccounting")
    @Primary
    public EntityManager entityManagerAccounting(EntityManagerFactoryBuilder builder) {

        EntityManagerFactory entityManagerFactory = entityManagerFactoryAccounting(builder).getNativeEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }


}
