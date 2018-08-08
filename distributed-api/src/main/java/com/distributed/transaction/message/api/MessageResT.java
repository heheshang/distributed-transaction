package com.distributed.transaction.message.api;

import lombok.Data;
import lombok.ToString;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-08-上午 9:48
 */
@Data
@ToString
public class MessageResT<M> {

    private M message;
}
