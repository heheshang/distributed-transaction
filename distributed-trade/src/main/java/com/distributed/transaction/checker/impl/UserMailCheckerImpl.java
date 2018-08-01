package com.distributed.transaction.checker.impl;

import com.distributed.transaction.BaseMessage;
import com.distributed.transaction.BaseParam;
import com.distributed.transaction.annotations.VerifyUser;
import com.distributed.transaction.checker.IChecker;
import com.distributed.transaction.exception.DistributedException;
import com.distributed.transaction.trade.api.recharge.RechargeMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import static com.distributed.transaction.enums.verify.UserVerifyEnum.USER_MAIL;

/**
*
* @author ssk www.8win.com Inc.All rights reserved
* @date 2018/07/30 上午 11:21
* @since v1.0
**/


@VerifyUser(check = {USER_MAIL})
@Component
@Log4j2
public class UserMailCheckerImpl  implements IChecker<BaseParam, BaseMessage> {


    @Override
    public BaseMessage check(BaseParam param) throws DistributedException {

        log.info("用户邮箱校验开始");
        RechargeMessage message = new RechargeMessage();

        return message;
    }




}
