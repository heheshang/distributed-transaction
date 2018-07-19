package com.distributed.transaction.service;

import com.distributed.transaction.annotations.MessageTran;
import com.distributed.transaction.utils.OptEnum;
import com.distributed.transaction.utils.TransEnum;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 消息操作包装器
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @date 2018/07/05 下午 3:17
 * @since v1.0
 **/

@Component
@Log4j2
public class TranMessageWrapperRegister extends ApplicationObjectSupport {


    private final static Table<TransEnum, OptEnum, IOptMessage> TRANS_MESSAGE_TABLE = HashBasedTable.create();


    @Override
    protected void initApplicationContext(ApplicationContext context) throws BeansException {

        super.initApplicationContext(context);

        Map<String, Object> taskBeanMap = context.getBeansWithAnnotation(MessageTran.class);

        taskBeanMap.keySet().forEach(beanName -> {

            Object bean = taskBeanMap.get(beanName);

            Class clazz = bean.getClass();

            if (bean instanceof IOptMessage && clazz.getAnnotation(MessageTran.class) != null) {

                MessageTran messageOpt = (MessageTran) clazz.getAnnotation(MessageTran.class);


                TransEnum tranCode = messageOpt.tran();

                OptEnum optEnum = messageOpt.opt();

                if (TRANS_MESSAGE_TABLE.contains(tranCode, optEnum)) {
                    log.error("transCode and optType  has Exits. tracode=[{}]", tranCode);
                    throw new RuntimeException("transCode and optType  has Exits. ");
                }

                TRANS_MESSAGE_TABLE.put(tranCode, optEnum, (IOptMessage) taskBeanMap.get(beanName));

                log.info("TranMessageWrapper Wrapper Register. transCode=[{}],beanName=[{}]", tranCode, beanName);

            }

        });
    }


    public static IOptMessage getTransMessage(TransEnum transCode, OptEnum optEnum) {

        return TRANS_MESSAGE_TABLE.get(transCode, optEnum);
    }
}
