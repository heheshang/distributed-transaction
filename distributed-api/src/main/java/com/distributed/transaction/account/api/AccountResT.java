package com.distributed.transaction.account.api;

import com.distributed.transaction.BaseMessage;
import lombok.Data;
import lombok.ToString;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-26-下午 4:38
 */
@Data
@ToString
public class AccountResT<M extends BaseMessage> {

    private M message;

    private Boolean success;

    private String procCode;

    private String procDesc;



}
