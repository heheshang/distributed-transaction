/*
package com.distributed.transaction.config;

import org.springframework.beans.factory.annotation.Autowired;
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

*/
/** 单节点配置
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-13-下午 2:31
 *//*


# 数据库基本配置
#spring.datasource.url=jdbc:mysql://127.0.0.1:3306/pay_message?characterEncoding=utf8&useSSL=false
#spring.datasource.username=root
#spring.datasource.password=123456
#spring.datasource.driver-class-name=com.mysql.jdbc.Driver
#spring.jpa.database=MYSQL


#datasource
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.jdbcUrl=jdbc:mysql://localhost:3306
spring.datasource.dataSourceClassName=com.mysql.jdbc.jdbc2.optional.MysqlDataSource
spring.datasource.dataSourceProperties.databaseName=pay_order
spring.datasource.dataSourceProperties.useSSL=false
spring.datasource.dataSourceProperties.useUnicode=true
spring.datasource.dataSourceProperties.characterEncoding=utf8
#指定连接池名字
spring.datasource.pool-name=jpa-pool
spring.datasource.maximum-pool-size=100
#自动提交
spring.datasource.default-auto-commit=true
spring.datasource.auto-commit=true
spring.datasourc.validation-query=SELECT 1

# Hibernate ddl auto (create, create-drop, update)
spring.jpa.hibernate.ddl-auto=update
#spring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect
spring.jpa.hibernate.naming_strategy=org.hibernate.cfg.ImprovedNamingStrategy
#spring.jpa.database=org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
spring.jpa.show-sql = true





@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager",
        //设置Repository所在位置
        basePackages = {"com.distributed.transaction.module.trade.repository"})
public class JpaConfig {

    @Autowired
    private DataSource dataSource;

    @Bean(name = "entityManager")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {

        return entityManagerFactory(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {

        return builder
                .dataSource(dataSource)
                .properties(getVendorProperties(dataSource))
                //设置实体类所在位置
                .packages("com.distributed.transaction.module.trade.domain")
//                .persistenceUnit("primaryPersistenceUnit")
                .build();
    }

    @Autowired
    private JpaProperties jpaProperties;

    private Map<String, String> getVendorProperties(DataSource dataSource) {

        return jpaProperties.getHibernateProperties(dataSource);
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {

        return new JpaTransactionManager(entityManagerFactory(builder).getObject());
    }

}
*/
