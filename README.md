# distributed-transaction

### jpa 使用说明
- 1 参照 如下配置三个数据库的连接配置

```properties

################ dataSource START #################
#primary
spring.datasource.primary.username=root
spring.datasource.primary.password=123456
spring.datasource.primary.jdbcUrl=jdbc:mysql://localhost:3306
spring.datasource.primary.dataSourceClassName=com.mysql.jdbc.jdbc2.optional.MysqlDataSource
spring.datasource.primary.dataSourceProperties.databaseName=pay_message
spring.datasource.primary.dataSourceProperties.useSSL=false
spring.datasource.primary.dataSourceProperties.useUnicode=true
spring.datasource.primary.dataSourceProperties.characterEncoding=utf8
#指定连接池名字
spring.datasource.primary.pool-name=pay-order-pool-primary
spring.datasource.primary.maximum-pool-size=100
#自动提交
spring.datasource.primary.default-auto-commit=true
spring.datasource.primary.auto-commit=true
spring.datasource.primary.validation-query=SELECT 1


#secondary
spring.datasource.secondary.username=root
spring.datasource.secondary.password=123456
spring.datasource.secondary.jdbcUrl=jdbc:mysql://localhost:3306
spring.datasource.secondary.dataSourceClassName=com.mysql.jdbc.jdbc2.optional.MysqlDataSource
spring.datasource.secondary.dataSourceProperties.databaseName=pay_order
spring.datasource.secondary.dataSourceProperties.useSSL=false
spring.datasource.secondary.dataSourceProperties.useUnicode=true
spring.datasource.secondary.dataSourceProperties.characterEncoding=utf8
#指定连接池名字
spring.datasource.secondary.pool-name=pay-order-pool-secondary
spring.datasource.secondary.maximum-pool-size=100
#自动提交
spring.datasource.secondary.default-auto-commit=true
spring.datasource.secondary.auto-commit=true
spring.datasource.secondary.validation-query=SELECT 1



#tertiary
spring.datasource.tertiary.username=root
spring.datasource.tertiary.password=123456
spring.datasource.tertiary.jdbcUrl=jdbc:mysql://localhost:3306
spring.datasource.tertiary.dataSourceClassName=com.mysql.jdbc.jdbc2.optional.MysqlDataSource
spring.datasource.tertiary.dataSourceProperties.databaseName=pay_product
spring.datasource.tertiary.dataSourceProperties.useSSL=false
spring.datasource.tertiary.dataSourceProperties.useUnicode=true
spring.datasource.tertiary.dataSourceProperties.characterEncoding=utf8
#指定连接池名字
spring.datasource.tertiary.pool-name=pay-order-pool-tertiary
spring.datasource.tertiary.maximum-pool-size=100
#自动提交
spring.datasource.tertiary.default-auto-commit=true
spring.datasource.tertiary.auto-commit=true
spring.datasource.tertiary.validation-query=SELECT 1
############# dataSource END #############



# Hibernate ddl auto (create, create-drop, update)
spring.jpa.hibernate.ddl-auto=update
#spring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect
spring.jpa.hibernate.naming_strategy=org.hibernate.cfg.ImprovedNamingStrategy
#spring.jpa.database=org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
spring.jpa.show-sql = true

```

- 2 datasoure 配置
```java

package com.distributed.transation.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
*
* @author ssk www.8win.com Inc.All rights reserved
* @date 2018/07/19 下午 12:29
* @since v1.0
**/


@Configuration
public class DataSourceConfig {


    @Bean(name = "primaryDataSource")
    @Primary
    @Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDatasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "tertiaryDataSource")
    @Qualifier("tertiaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.tertiary")
    public DataSource tertiaryDataSource() {
        return DataSourceBuilder.create().build();
    }
}


```

- 3 数据库配置

```java

package com.distributed.transation.config;

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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Map;


/**
* 将数据源注入到 实体管理器工厂，配置 base、domian 的位置
* @author ssk www.8win.com Inc.All rights reserved
* @date 2018/07/19 下午 1:46
* @since v1.0
**/
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryPrimary",//配置连接工厂 entityManagerFactory
        transactionManagerRef = "transactionManagerPrimary", //配置 事物管理器  transactionManager
        basePackages = {"com.distributed.transaction.module.message.repository"}//设置dao（repo）所在位置
)
public class PrimaryConfig {

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    @Qualifier("primaryDataSource")
    private DataSource primaryDataSource;


    /**
     *
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
                .packages("com.distributed.transaction.module.message.domain")
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

    /**
     * 本地查询或者取得序列号执行存储过程使用
     * @param builder
     * @return
     */
    @Bean(name = "entityManagerPrimary")
    @Primary
    public EntityManager entityManagerPrimary(EntityManagerFactoryBuilder builder) {

        EntityManagerFactory entityManagerFactory = entityManagerFactoryPrimary(builder).getNativeEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }
}

```
- 4 实体配置
  - 必须实现 Serializable 接口才能进行动态的字段更新 