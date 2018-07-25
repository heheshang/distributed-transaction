package com.distributed.transaction.service.recharge;

import com.distributed.transaction.register.TransType;
import com.distributed.transaction.register.TransTypeEnum;
import com.distributed.transaction.service.BaseTranService;
import lombok.extern.log4j.Log4j2;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-25-下午 3:04
 */
@TransType(value = TransTypeEnum.TEST_RECHARGE_PAY)
@Log4j2
public class TestPayTranServiceImpl extends BaseTranService {

    @Override
    public void handle() {

    }
}
