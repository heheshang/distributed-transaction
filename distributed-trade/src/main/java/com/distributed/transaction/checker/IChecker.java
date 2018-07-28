package com.distributed.transaction.checker;

import com.distributed.transaction.BaseMessage;
import com.distributed.transaction.BaseParam;

/**
 * 校验器
 * Created by Administrator on 2018/7/28.
 */
public interface IChecker<P extends BaseParam, M extends BaseMessage> {

    public void check(P p, M m);

}
