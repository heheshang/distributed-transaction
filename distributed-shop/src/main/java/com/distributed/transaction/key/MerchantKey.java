package com.distributed.transaction.key;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-23-下午 3:00
 */
@Data
@AllArgsConstructor
public class MerchantKey {

    private String payKey;

    private String paySecret;
}
