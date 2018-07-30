package com.distributed.transaction.checker;

import com.distributed.transaction.BaseMessage;
import com.distributed.transaction.BaseParam;
import com.distributed.transaction.exception.DistributedExceprion;

/**
 * 校验器
 * Created by Administrator on 2018/7/28.
 */
public interface IChecker<P extends BaseParam, M extends BaseMessage> {

    public M check(P p) throws DistributedExceprion;


    default M exce(P p) throws DistributedExceprion{


        return check(p);

    }

}
