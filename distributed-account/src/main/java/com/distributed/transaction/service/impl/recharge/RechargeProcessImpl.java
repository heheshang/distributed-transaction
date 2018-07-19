package com.distributed.transaction.service.impl.recharge;

import com.distributed.transaction.annotations.TransType;
import com.distributed.transaction.api.trans.recharge.RechargeMessage;
import com.distributed.transaction.api.trans.recharge.RechargeParam;
import com.distributed.transaction.exception.DistributedExceprion;
import com.distributed.transaction.service.BaseTranProcessApi;
import com.distributed.transaction.utils.TransEnum;

/**
 * 充值处理器
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-04-下午 1:28
 */
@TransType(type = TransEnum.RECHAEGE)
public class RechargeProcessImpl extends BaseTranProcessApi<RechargeParam,RechargeMessage> {

    @Override
    public RechargeMessage execute(RechargeParam param) throws DistributedExceprion {
        RechargeMessage message = new RechargeMessage();

        message.setSuccess(true);
        return message;
    }
}
