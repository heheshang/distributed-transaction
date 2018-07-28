package com.distributed.transaction.register;

import com.distributed.transaction.annotations.VerifyUser;
import com.distributed.transaction.checker.IChecker;
import com.distributed.transaction.service.ITranService;
import com.distributed.transaction.utils.TransTypeEnum;
import com.distributed.transaction.utils.UserVerifyEnum;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

/**
 * 交易类型包装器
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @date 2018/07/05 下午 3:17
 * @since v1.0
 **/

@Component
@Log4j2
public class VerifyServiceRegister extends ApplicationObjectSupport {


    @Override
    protected void initApplicationContext(ApplicationContext context) throws BeansException {

        super.initApplicationContext(context);

        Map<String, Object> verifyUserBeanMap = context.getBeansWithAnnotation(VerifyUser.class);

        verifyUserBeanMap.keySet().forEach((String beanName) -> {

            Object bean = verifyUserBeanMap.get(beanName);

            Class clazz = bean.getClass();

            if (bean instanceof IChecker && clazz.getAnnotation(VerifyUser.class) != null) {

                VerifyUser verifyUserAnno = (VerifyUser) clazz.getAnnotation(VerifyUser.class);


                UserVerifyEnum[] tranType = verifyUserAnno.check();

                for (UserVerifyEnum verifyEnum : tranType) {
                    log.info("发现注册 component Register. verifyEnum=[{}]", verifyEnum);

                    tranServiceCache.asMap().put(verifyEnum.getAuthCode(), (IChecker) bean);
                }

            }

        });
    }


    private LoadingCache<String, IChecker> tranServiceCache = CacheBuilder
            .newBuilder()
            .maximumSize(20)
            .build(new CacheLoader<String, IChecker>() {

                @Override
                public IChecker load(String key) throws Exception {
                    return null;
                }
            });


}
