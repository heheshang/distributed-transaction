package com.distributed.transaction.repository.test1;


import com.distributed.transaction.api.repository.BaseRepository;
import com.distributed.transaction.entity.m.TransactionMessage;
import org.springframework.stereotype.Repository;

/**
 * 消息接口
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-05-上午 11:59
 */
@Repository
public interface TransactionMessageRepository extends BaseRepository<TransactionMessage, String> {

}
