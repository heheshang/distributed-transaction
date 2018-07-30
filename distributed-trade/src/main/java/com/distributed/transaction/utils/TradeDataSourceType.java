package com.distributed.transaction.utils;

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
public enum TradeDataSourceType {
    /**
     *
     */
    MASTER("pay_order"),
    /**
     *
     */
    SLAVE("pay_product");


    public String name;

    TradeDataSourceType(String test) {

        this.name = test;

    }
}
