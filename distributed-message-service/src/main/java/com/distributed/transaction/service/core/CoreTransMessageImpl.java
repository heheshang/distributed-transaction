package com.distributed.transaction.service.core;

import com.distributed.transaction.service.BaseTransMessage;
import com.distributed.transaction.service.IOptMessage;
import com.distributed.transaction.service.TranMessageWrapperRegister;
import com.distributed.transaction.tran.TranReq;
import com.distributed.transaction.tran.TranRes;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-05-下午 6:07
 */
@Component
@Log4j2
public class CoreTransMessageImpl extends BaseTransMessage {

    @Override
    public TranRes process(TranReq req) {

        IOptMessage optMessage = TranMessageWrapperRegister.getTransMessage(req.getTransEnum(), req.getOptEnum());

        TranRes res = new TranRes();

        res.setOptEnum(req.getOptEnum());

        res.setTransEnum(req.getTransEnum());


        if (optMessage == null) {


            log.error("获取消息操作实现接口为空");

            res.setSuccess(false);

            res.setRetCode("4444");

            res.setRetDesc("获取消息操作实现接口为空");


            return res;

        }
        res.setSuccess(true);

        res.setRetCode("0000");

        res.setRetDesc("操作成功");

        res = optMessage.process(req, res);

        return res;
    }
}
