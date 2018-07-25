package com.distributed.transaction;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.distributed.transaction"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication
@Log4j2
public class DistributedMessageApplication {

    public static void main(String[] args) {

       ApplicationContext applicationContext= SpringApplication.run(DistributedMessageApplication.class, args);

        DataSource dataSource = applicationContext.getBean(DataSource.class);
        log.info("datasource is :" + dataSource);
        //检查数据库是否是hikar数据库连接池
        if (!(dataSource instanceof HikariDataSource)) {
            log.error(" Wrong datasource type :"
                    + dataSource.getClass().getCanonicalName());
            System.exit(-1);
        }
        try {
            Connection connection = dataSource.getConnection();
            ResultSet rs = connection.createStatement()
                    .executeQuery("SELECT 1");
            if (rs.first()) {

                log.info("Connection OK!");
            } else {
                log.error("Something is wrong");
            }
            // connection.close();
            // System.exit(0);

        } catch (SQLException e) {
            log.error("FAILED");
            e.printStackTrace();
            System.exit(-2);
            // TODO: handle exception
        }

    }


}
