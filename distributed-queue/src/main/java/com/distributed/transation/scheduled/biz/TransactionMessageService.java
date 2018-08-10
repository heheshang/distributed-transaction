package com.distributed.transation.scheduled.biz;

import com.distributed.transaction.enums.message.MessageStatusEnum;
import com.distributed.transaction.module.message.domain.TransactionMessageEntity;
import com.distributed.transaction.module.message.repository.TransactionMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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


    public Page<TransactionMessageEntity> getTransactionMessage(int pageNum,int pageSize,final Map<String, Object> queryMap) {
        //后台得不到数据, 并提示 Page 1 of 1 containing UNKNOWN instances
        //
        //
        //
        //原因page从0开始不是从1开始
        //
        //解决方式把page修改为0
        Sort sort = new Sort(Sort.Direction.ASC,"createTime");

        Pageable pageable = new PageRequest(pageNum,pageSize,sort);

        Page<TransactionMessageEntity> page= transactionMessageRepository.findAll(new Specification<TransactionMessageEntity>() {
            @Override
            public Predicate toPredicate(Root<TransactionMessageEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {


                List<Predicate> list = new ArrayList<Predicate>();

                for (String key : queryMap.keySet()) {
                    if (null!= queryMap.get(key)&&!"".equals(queryMap.get(key))){

                        if (queryMap.get(key) instanceof MessageStatusEnum){

                            list.add(criteriaBuilder.equal(root.get(key).as(MessageStatusEnum.class), queryMap.get(key)));
                        }else {
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

}
