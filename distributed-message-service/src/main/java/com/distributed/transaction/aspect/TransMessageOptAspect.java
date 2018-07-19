package com.distributed.transaction.aspect;

import com.distributed.transaction.entity.s.TransactionMessage;
import com.distributed.transaction.repository.QuerBysqlService;
import com.distributed.transaction.repository.test2.TransactionMessageRepository1;
import com.distributed.transaction.tran.TranReq;
import com.distributed.transaction.tran.TranRes;
import com.distributed.transaction.tran.TransactionMessageVo;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息操作处理
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-05-下午 3:41
 */
@Component
@Aspect
@Log4j2
public class TransMessageOptAspect {

    @Autowired
    private TransactionMessageRepository1 transactionMessageRepository;

    @Autowired
    private QuerBysqlService querBysqlService;

    @Autowired
    private DozerBeanMapper mapper;

    @Before("@within(com.distributed.transaction.annotations.MessageTran)")
    public void doAroundAdvice(JoinPoint joinPoint) {

        TranRes res = null;

        Object[] args = joinPoint.getArgs();

        if (args[0] instanceof TranReq && args[1] instanceof TranRes) {

            TranReq req = (TranReq) args[0];

            res = (TranRes) args[1];
            TransactionMessageVo vo;
            TransactionMessage message;
            switch (req.getOptEnum()) {
                case SAVE:
                    vo = (TransactionMessageVo) req.getParam();

                    message = mapper.map(vo, TransactionMessage.class);

                    res.setMessage(mapper.map(transactionMessageRepository.save(message), TransactionMessageVo.class));
                    break;
                case QUERY:
                  //  res.setMessage(querBysqlService.findAllByViaQuery(" select * from transaction_message", (Map<String, Object>) req.getParam()));

                    break;
                case UPDATE:
                    vo = (TransactionMessageVo) req.getParam();

                    vo =  querBysqlService.update(vo.getId());
//                    message1.setStatus(vo.getStatus());
                    res.setMessage(vo);
                    break;
                default:
                    break;
            }
        }
        args[1] = res;
        return;
    }


}
