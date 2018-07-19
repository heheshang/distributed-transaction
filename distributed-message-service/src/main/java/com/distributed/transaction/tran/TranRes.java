package com.distributed.transaction.tran;

import com.distributed.transaction.utils.OptEnum;
import com.distributed.transaction.utils.TransEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-05-下午 2:44
 */
@Data
public class TranRes<M> implements Serializable {

    private M message;

    private boolean isSuccess = false;

    private String retCode;

    private String retDesc;

    private TransEnum transEnum;

    private OptEnum optEnum;
}
