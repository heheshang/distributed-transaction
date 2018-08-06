package com.distributed.transaction;

/**
 * 列出所有的数据源key（常用数据库名称来命名）
 * * 注意：
 * * 1）这里数据源与数据库是一对一的
 * * 2）DatabaseType中的变量名称就是数据库的名称
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-18-下午 5:44
 */
public enum DatabaseType {
    /**
     *
     */
    MASTER("pay_message"),
    /**
     *
     */
    SLAVE1("pay_message1");


    public String name;

    DatabaseType(String test) {

        this.name = test;

    }
}
