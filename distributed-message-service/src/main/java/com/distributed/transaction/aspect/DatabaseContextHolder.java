package com.distributed.transaction.aspect;

import com.distributed.transaction.DatabaseType;
import lombok.extern.log4j.Log4j2;

/**
 * 1、保存一个线程安全的DatabaseType容器
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-18-下午 5:45
 */
@Log4j2
public class DatabaseContextHolder {

    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();


    // 设置数据源名
    public static void setDB(DatabaseType dbType) {

        log.error("切换到{}数据源", dbType);
        contextHolder.set(dbType);
    }

    // 获取数据源名
    public static DatabaseType getDB() {

        return (contextHolder.get());
    }

    // 清除数据源名
    public static void clearDB() {

        contextHolder.remove();
    }
}
