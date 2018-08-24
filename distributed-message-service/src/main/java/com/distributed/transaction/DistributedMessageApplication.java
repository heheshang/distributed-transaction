package com.distributed.transaction;

import com.distributed.transaction.config.HttpClientConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
*
* @author ssk www.8win.com Inc.All rights reserved
* @date 2018/08/08 上午 10:04
* @since v1.0
**/
@EnableDiscoveryClient
@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication
@EnableFeignClients(basePackages = { "com.distributed.transaction.message" })
@Log4j2
@ImportAutoConfiguration(classes = HttpClientConfig.class)
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
