package com.distributed.transaction.checker.impl;

import com.distributed.transaction.BaseMessage;
import com.distributed.transaction.BaseParam;
import com.distributed.transaction.annotations.VerifyUser;
import com.distributed.transaction.checker.IChecker;
import com.distributed.transaction.exception.DistributedExceprion;
import com.distributed.transaction.trade.api.recharge.RechargeMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import static com.distributed.transaction.utils.UserVerifyEnum.USER_AUTH;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @date 2018/07/30 上午 11:21
 * @since v1.0
 **/
@VerifyUser(check = {USER_AUTH})
@Component
@Log4j2
public class UserAuthCheckerImpl implements IChecker<BaseParam, BaseMessage> {

    @Override
    public BaseMessage check(BaseParam baseParam) throws DistributedExceprion {

        log.info("用户校验开始");
        RechargeMessage message = new RechargeMessage();
        throw new DistributedExceprion("1111","2222",this.getClass(),new DistributedExceprion());
    }




}
