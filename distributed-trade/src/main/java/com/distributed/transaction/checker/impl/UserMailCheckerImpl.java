package com.distributed.transaction.checker.impl;

import com.distributed.transaction.annotations.VerifyUser;
import com.distributed.transaction.checker.IChecker;
import com.distributed.transaction.trade.api.recharge.RechargeMessage;
import com.distributed.transaction.trade.api.recharge.RechargeParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import static com.distributed.transaction.utils.UserVerifyEnum.USER_AUTH;
import static com.distributed.transaction.utils.UserVerifyEnum.USER_MAIL;

/**
 * Created by Administrator on 2018/7/28.
 */
@VerifyUser(check = {USER_MAIL})
@Component
@Log4j2
public class UserMailCheckerImpl implements IChecker<RechargeParam, RechargeMessage> {
    @Override
    public void check(RechargeParam baseParam, RechargeMessage baseMessage) {
       log.info("用户校验开始");
    }
}
