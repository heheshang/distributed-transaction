package com.distributed.transaction.service.impl;

import com.distributed.transaction.annotations.MessageTran;
import com.distributed.transaction.service.IOptMessage;
import com.distributed.transaction.tran.TranReq;
import com.distributed.transaction.tran.TranRes;
import com.distributed.transaction.utils.TransEnum;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-05-下午 3:23
 */
@MessageTran(tran=TransEnum.WITHDRAW)
public class WithdrawOptMessageImpl implements IOptMessage {

    @Override
    public TranRes process(TranReq req, TranRes res) {

        System.out.println("ssssssssssssssssssssss1111");
        return res;
    }
}
