package com.distributed.transaction.register;

import com.distributed.transaction.annotations.VerifyProd;
import com.distributed.transaction.annotations.VerifyUser;
import com.distributed.transaction.service.ITranService;
import com.distributed.transaction.service.recharge.AliPayTranServiceImpl;
import com.distributed.transaction.service.recharge.TestPayTranServiceImpl;
import com.distributed.transaction.service.recharge.WeChartPayTranServiceImpl;
import com.distributed.transaction.utils.TransTypeEnum;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

/**
 * 交易类型包装器
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @date 2018/07/05 下午 3:17
 * @since v1.0
 **/

@Component
@Log4j2
public class TranServiceComponentRegister /*extends ApplicationObjectSupport*/ {


    private final static Map<TransTypeEnum, ITranService> TRANS_MESSAGE_MAP = Maps.newHashMap();


   /* @Override
    protected void initApplicationContext(ApplicationContext context) throws BeansException {

        super.initApplicationContext(context);

        Map<String, Object> taskBeanMap = context.getBeansWithAnnotation(TradeTransType.class);

        taskBeanMap.keySet().forEach(beanName -> {

            Object bean = taskBeanMap.get(beanName);

            Class clazz = bean.getClass();

            if (bean instanceof ITranService && clazz.getAnnotation(TradeTransType.class) != null) {

                TradeTransType transType = (TradeTransType) clazz.getAnnotation(TradeTransType.class);


                TransTypeEnum tranType = transType.value();


                if (TRANS_MESSAGE_MAP.containsKey(tranType)) {
                    log.error("tranType  has Exits. tranType=[{}]", tranType);
                    throw new RuntimeException("transCode and optType  has Exits. ");
                }

                TRANS_MESSAGE_MAP.put(tranType, (ITranService) taskBeanMap.get(beanName));

                log.info("TranServiceComponentRegister component Register. tranType=[{}]", tranType);

            }

        });
    }*/


    private static Map<String, Class<? extends ITranService>> servicesMapping = new ConcurrentHashMap<String, Class<? extends ITranService>>() {
        private static final long serialVersionUID = -8076694302460548904L;

        {
            put(TransTypeEnum.ALI_RECHARGE_PAY.value, AliPayTranServiceImpl.class);
            put(TransTypeEnum.WECHAT_RECHARGE_PAY.value, WeChartPayTranServiceImpl.class);
            put(TransTypeEnum.TEST_RECHARGE_PAY.value, TestPayTranServiceImpl.class);

        }
    };

    public ITranService getTransMessage(TransTypeEnum transType) {

        try {
            return tranServiceCache.get(transType.value);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Autowired
    private ApplicationContext applicationContext;

    private LoadingCache<String, ITranService> tranServiceCache = CacheBuilder
            .newBuilder()
            .maximumSize(20)
            .build(new CacheLoader<String, ITranService>() {
                @Override
                public ITranService load(String key) throws Exception {

                    log.info("加载注册bean");

                    if (!servicesMapping.containsKey(Objects.requireNonNull(TransTypeEnum.getByValue(key)).value)) {
                        return null;
                    }
                    return applicationContext.getBean(servicesMapping.get(key));
                }
            });

    private LoadingCache<Method, VerifyUser> verifyUserAnnoCache = CacheBuilder
            .newBuilder()
            .build(new CacheLoader<Method, VerifyUser>() {
                @Override
                public VerifyUser load(Method method) throws Exception {

                    return AnnotationUtils.findAnnotation(method, VerifyUser.class);
                }
            });

    public VerifyUser getVerifyUserAnnoCache(Method method) {

        try {
            return verifyUserAnnoCache.get(method);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private LoadingCache<Method, VerifyProd> verifyProductAnnoCache = CacheBuilder
            .newBuilder()
            .build(new CacheLoader<Method, VerifyProd>() {
                @Override
                public VerifyProd load(Method method) throws Exception {

                    return AnnotationUtils.findAnnotation(method, VerifyProd.class);
                }
            });


    public VerifyProd getVerifyProductAnnoCache(Method method) {

        try {
            return verifyProductAnnoCache.get(method);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
