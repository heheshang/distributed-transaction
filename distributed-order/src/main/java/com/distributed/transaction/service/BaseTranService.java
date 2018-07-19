package com.distributed.transaction.service;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-04-下午 2:05
 */
public abstract class BaseTranService implements ITranService {

    @Override
    public void process() {
        handle();
    }

    public abstract void handle();
}
