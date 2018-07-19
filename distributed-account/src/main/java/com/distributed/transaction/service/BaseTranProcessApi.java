package com.distributed.transaction.service;

import com.distributed.transaction.api.trans.BaseMessage;
import com.distributed.transaction.api.trans.BaseParam;
import com.distributed.transaction.api.trans.ReqT;
import com.distributed.transaction.api.trans.ResT;
import com.distributed.transaction.exception.DistributedExceprion;
import lombok.extern.log4j.Log4j2;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-04-上午 11:39
 */
@Log4j2
public abstract class BaseTranProcessApi<P extends BaseParam, M extends BaseMessage> implements ITranProcessApi<P, M> {

    @Override
    public ResT<M> handle(ReqT<P> req) {

        ResT<M> response = new ResT<M>();
        try {
            M message = execute(req.getParams());
            response.setSuccess(message.isSuccess());
            response.setProcCode("0000");
            response.setProcDesc("交易成功");
            response.setMessage(message);
        } catch (Exception e) {

            String errorCode = "";
            String errorDesc = "";
            if (e instanceof DistributedExceprion) {
                DistributedExceprion _e = (DistributedExceprion) e;
                errorCode = _e.getErrCode();
                errorDesc = _e.getErrMsg();
            } else {
                log.error("系统异常");
                errorCode = "001";
                errorDesc = "001";
            }
            response.setSuccess(Boolean.FALSE);
            response.setMessage(null);
            response.setProcCode(errorCode);
            response.setProcDesc(errorDesc);

        }
        return response;
    }

    public abstract M execute(P p) throws DistributedExceprion;
}


