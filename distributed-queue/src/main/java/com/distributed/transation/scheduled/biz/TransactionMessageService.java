package com.distributed.transation.scheduled.biz;

import com.distributed.transaction.enums.message.AreadlyDeadEnum;
import com.distributed.transaction.enums.message.MessageStatusEnum;
import com.distributed.transaction.exception.MessageBizException;
import com.distributed.transaction.module.message.domain.TransactionMessageEntity;
import com.distributed.transaction.module.message.repository.TransactionMessageRepository;
import com.distributed.transaction.module.message.vo.TransactionMessage;
import com.distributed.transaction.utils.BeanMapping;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 分页查询
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-10-上午 11:21
 */
@Component
public class TransactionMessageService {

    @Autowired
    private TransactionMessageRepository transactionMessageRepository;
    @Autowired
    RabbitTemplate firstRabbitTemplate;


    public Page<TransactionMessageEntity> getTransactionMessage(int pageNum, int pageSize, final Map<String, Object> queryMap) {
        //后台得不到数据, 并提示 Page 1 of 1 containing UNKNOWN instances
        //
        //
        //
        //原因page从0开始不是从1开始
        //
        //解决方式把page修改为0
        Sort sort = new Sort(Sort.Direction.ASC, "createTime");

        Pageable pageable = PageRequest.of(pageNum, pageSize, sort);

        Page<TransactionMessageEntity> page = transactionMessageRepository.findAll(new Specification<TransactionMessageEntity>() {
            @Override
            public Predicate toPredicate(Root<TransactionMessageEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {


                List<Predicate> list = new ArrayList<Predicate>();

                for (String key : queryMap.keySet()) {
                    if (null != queryMap.get(key) && !"".equals(queryMap.get(key))) {

                        if (queryMap.get(key) instanceof MessageStatusEnum) {

                            list.add(criteriaBuilder.equal(root.get(key).as(MessageStatusEnum.class), queryMap.get(key)));

                        } else if (queryMap.get(key) instanceof AreadlyDeadEnum) {

                            list.add(criteriaBuilder.equal(root.get(key).as(AreadlyDeadEnum.class), queryMap.get(key)));
                        } else {
                            list.add(criteriaBuilder.equal(root.get(key).as(String.class), queryMap.get(key)));
                        }
                    }
                }


                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));


            }
        }, pageable);


        return page;
    }

    @Transactional(rollbackFor = Exception.class)
    public void confirmAndSendMessage(String messageId) {

        TransactionMessageEntity messageEntity = transactionMessageRepository.getByMessageId(messageId);

        TransactionMessage transactionMessage = BeanMapping.map(messageEntity, TransactionMessage.class);

        messageEntity.setStatus(MessageStatusEnum.SENDING);

        firstRabbitTemplate.convertAndSend("", transactionMessage);


    }


    @Transactional(rollbackFor = Exception.class)
    public void deleteMessageByMessageId(String messageId) {

        transactionMessageRepository.deleteByMessageId(messageId);

    }

    @Transactional(rollbackFor = Exception.class)
    public void reSendMessage(final TransactionMessage message) {

        if (message == null) {
            throw new MessageBizException(MessageBizException.SAVA_MESSAGE_IS_NULL, "保存的消息为空");
        }

        if (StringUtils.isEmpty(message.getConsumerQueue().name())) {
            throw new MessageBizException(MessageBizException.MESSAGE_CONSUMER_QUEUE_IS_NULL, "消息的消费队列不能为空 ");
        }

        TransactionMessageEntity messageEntity = transactionMessageRepository.getByMessageId(message.getMessageId());

        messageEntity.setMessageSendTimes(message.getMessageSendTimes() + 1);

        messageEntity.setEditTime(new Date());

        firstRabbitTemplate.convertAndSend("", messageEntity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void setMessageToAreadlyDead(String messageId) {

        TransactionMessageEntity message = transactionMessageRepository.getByMessageId(messageId);
        if (message == null) {
            throw new MessageBizException(MessageBizException.SAVA_MESSAGE_IS_NULL, "根据消息id查找的消息为空");
        }

        message.setAreadlyDead(AreadlyDeadEnum.YES);
        message.setEditTime(new Date());
    }
}
