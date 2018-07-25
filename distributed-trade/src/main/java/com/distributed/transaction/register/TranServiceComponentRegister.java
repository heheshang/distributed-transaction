package com.distributed.transaction.register;

import com.distributed.transaction.service.ITranService;
import com.google.common.collect.Maps;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 交易类型包装器
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @date 2018/07/05 下午 3:17
 * @since v1.0
 **/

@Component
@Log4j2
public class TranServiceComponentRegister extends ApplicationObjectSupport {


    private final static Map<TransTypeEnum, ITranService> TRANS_MESSAGE_MAP = Maps.newHashMap();


    @Override
    protected void initApplicationContext(ApplicationContext context) throws BeansException {

        super.initApplicationContext(context);

        Map<String, Object> taskBeanMap = context.getBeansWithAnnotation(TransType.class);

        taskBeanMap.keySet().forEach(beanName -> {

            Object bean = taskBeanMap.get(beanName);

            Class clazz = bean.getClass();

            if (bean instanceof ITranService && clazz.getAnnotation(TransType.class) != null) {

                TransType transType = (TransType) clazz.getAnnotation(TransType.class);


                TransTypeEnum tranType = transType.value();


                if (TRANS_MESSAGE_MAP.containsKey(tranType)) {
                    log.error("tranType  has Exits. tranType=[{}]", tranType);
                    throw new RuntimeException("transCode and optType  has Exits. ");
                }

                TRANS_MESSAGE_MAP.put(tranType, (ITranService) taskBeanMap.get(beanName));

                log.info("TranServiceComponentRegister component Register. tranType=[{}]", tranType);

            }

        });
    }


    public static ITranService getTransMessage(TransTypeEnum transType) {

        return TRANS_MESSAGE_MAP.get(transType);
    }
}
