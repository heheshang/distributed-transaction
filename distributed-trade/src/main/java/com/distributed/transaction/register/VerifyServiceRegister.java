package com.distributed.transaction.register;

import com.distributed.transaction.annotations.VerifyUser;
import com.distributed.transaction.checker.IChecker;
import com.distributed.transaction.enums.verify.UserVerifyEnum;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 统一校验注册器
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

        for (String beanName : verifyUserBeanMap.keySet()) {
            Object bean = verifyUserBeanMap.get(beanName);

            Class clazz = bean.getClass();

            if (bean instanceof IChecker && clazz.getAnnotation(VerifyUser.class) != null) {

                VerifyUser verifyUserAnno = (VerifyUser) clazz.getAnnotation(VerifyUser.class);


                UserVerifyEnum[] tranType = verifyUserAnno.check();

                for (UserVerifyEnum verifyEnum : tranType) {


                    if (tranServiceCache.asMap().containsKey(verifyEnum.getAuthCode())) {

                        log.error("统一校验包装器 发现注册 bean 被注册不在进行重复注册. verifyEnum=[{}]", verifyEnum);

                    } else {

                        log.info("统一校验包装器 发现注册 bean be register. verifyEnum=[{}]", verifyEnum);

                        tranServiceCache.asMap().put(verifyEnum.getAuthCode(), (IChecker) bean);
                    }
                }

            }
        }
    }


    public  LoadingCache<String, IChecker> tranServiceCache = CacheBuilder
            .newBuilder()
            .maximumSize(200)
            .build(new CacheLoader<String, IChecker>() {

                @Override
                public IChecker load(String key) {
                    return null;
                }
            });


}
