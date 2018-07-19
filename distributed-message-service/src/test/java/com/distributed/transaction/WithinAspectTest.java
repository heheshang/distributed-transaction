package com.distributed.transaction;

import com.distributed.transaction.service.ITransMessage;
import com.distributed.transaction.tran.TranReq;
import com.distributed.transaction.tran.TranRes;
import com.distributed.transaction.tran.TransactionMessageVo;
import com.distributed.transaction.utils.AreadlyDeadEnum;
import com.distributed.transaction.utils.ConsumerQueueEnum;
import com.distributed.transaction.utils.MsgDataTypeEnum;
import com.distributed.transaction.utils.OptEnum;
import com.distributed.transaction.utils.TransEnum;
import com.google.common.collect.Maps;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Map;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-05-下午 5:03
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class WithinAspectTest {

    @Autowired
    ITransMessage transMessage;

    @Test
    public void test() {

        TranReq req = new TranReq();
      req.setOptEnum(OptEnum.UPDATE);
        req.setTransEnum(TransEnum.RECHAEGE);
        TransactionMessageVo vo = new TransactionMessageVo();
        vo.setId("297eb8c4646efd1401646efd32380000");
        vo.setEditor("张三");
        vo.setCreater("李四www");
//        vo.setEditTime(new Date());
//        vo.setCreateTime(new Date());
        vo.setMessageId("");
        vo.setMessageBody("");
        vo.setMsgDataType(MsgDataTypeEnum.JSON);
        vo.setConsumerQueue(ConsumerQueueEnum.RECHARGE_QUEUE);
        vo.setMessageSendTimes(0);
        vo.setAreadlyDead(AreadlyDeadEnum.NO);
        vo.setStatus("2222111112221222");
        vo.setRemark("111222");
        vo.setField1("");
        vo.setField2("");
        vo.setField3("");
        Map<String,Object> map = Maps.newHashMap();
        req.setParam(vo);

        try {
            TranRes res = (TranRes) transMessage.handle(req);
            log.info("返回参数为 [{}]",res);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
