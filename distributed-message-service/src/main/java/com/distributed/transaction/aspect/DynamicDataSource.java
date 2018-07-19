package com.distributed.transaction.aspect;

import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-19-上午 10:31
 */
@Log4j2
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * Determine the current lookup key. This will typically be
     * implemented to check a thread-bound transaction context.
     * <p>Allows for arbitrary keys. The returned key needs
     * to match the stored lookup key type, as resolved by the
     * {@link #resolveSpecifiedLookupKey} method.
     */
    @Override
    protected Object determineCurrentLookupKey() {
        log.info("Current DataSource is [{}]", DatabaseContextHolder.getDB());
        return DatabaseContextHolder.getDB();
    }
}
