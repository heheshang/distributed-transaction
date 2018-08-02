package com.distributed.transaction.base;

import com.distributed.transaction.DatabaseType;
import com.distributed.transaction.annotations.DS;
import com.distributed.transaction.enums.message.MessageStatusEnum;
import com.distributed.transaction.module.message.domain.TransactionMessageEntity;
import com.distributed.transaction.module.message.repository.TransactionMessageRepository;
import com.distributed.transaction.module.message.vo.TransactionMessage;
import com.distributed.transaction.tran.TransactionMessageVo;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-06-下午 3:57
 */
@Component
public class QuerBysqlService {

    @Autowired
    private DozerBeanMapper mapper;


    @Autowired
    private TransactionMessageRepository transactionMessageRepository;

    @DS(value = DatabaseType.MASTER)
    @Transactional
    public TransactionMessageVo save(TransactionMessageVo vo) {

        TransactionMessageEntity message = mapper.map(vo, TransactionMessageEntity.class);
        transactionMessageRepository.save(message);

        return mapper.map(transactionMessageRepository.save(message), TransactionMessageVo.class);
    }

    @DS(value = DatabaseType.MASTER)
    @Transactional
    public TransactionMessageVo update(String id) {

        TransactionMessageEntity message = transactionMessageRepository.findOne(id);

        message.setStatus(MessageStatusEnum.WAITING_CONFIRM);
        message.setCreater("xiaozhang");
//        transactionMessageRepository.save(message);
        return mapper.map(message, TransactionMessageVo.class);
    }

}
