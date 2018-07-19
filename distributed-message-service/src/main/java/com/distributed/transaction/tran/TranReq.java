package com.distributed.transaction.tran;

import com.distributed.transaction.utils.OptEnum;
import com.distributed.transaction.utils.TransEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-05-下午 2:43
 */
@Data
public class TranReq<P> implements Serializable {

    private P param;

    private TransEnum transEnum;

    private OptEnum optEnum;

}
