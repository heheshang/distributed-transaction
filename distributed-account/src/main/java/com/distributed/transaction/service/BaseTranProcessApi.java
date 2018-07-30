package com.distributed.transaction.service;


import com.distributed.transaction.BaseMessage;
import com.distributed.transaction.BaseParam;
import com.distributed.transaction.account.api.AccountReqT;
import com.distributed.transaction.account.api.AccountResT;
import com.distributed.transaction.exception.DistributedException;
import lombok.extern.log4j.Log4j2;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-04-上午 11:39
 */
@Log4j2
public abstract class BaseTranProcessApi<P extends BaseParam, M extends BaseMessage> implements ITranProcessApi<P, M> {

    @Override
    public AccountResT<M> handle(AccountReqT<P> req) {

        AccountResT<M> response = new AccountResT<M>();
        try {
            M message = execute(req.getParams());
            response.setSuccess(Boolean.TRUE);

            response.setMessage(message);
        } catch (Exception e) {

            String errorCode = "";
            String errorDesc = "";
            if (e instanceof DistributedException) {
                DistributedException _e = (DistributedException) e;
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

    public abstract M execute(P p) throws DistributedException;
}


