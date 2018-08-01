package com.distributed.transaction.register;

import com.distributed.transaction.annotations.VerifyProd;
import com.distributed.transaction.annotations.VerifyProdType;
import com.distributed.transaction.annotations.VerifyUser;
import com.distributed.transaction.service.ITranService;
import com.distributed.transaction.service.recharge.AliPayTranServiceImpl;
import com.distributed.transaction.service.recharge.TestPayTranServiceImpl;
import com.distributed.transaction.service.recharge.WeChartPayTranServiceImpl;
import com.distributed.transaction.enums.PayTypeEnum;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
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


//    private final static Map<TransTypeEnum, ITranService> TRANS_MESSAGE_MAP = Maps.newHashMap();


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
            put(PayTypeEnum.ALI_TEST.getWay(), AliPayTranServiceImpl.class);
            put(PayTypeEnum.WEIXIN_SCANPAY.getWay(), WeChartPayTranServiceImpl.class);
            put(PayTypeEnum.TEST_PAY_HTTP_CLIENT.getWay(), TestPayTranServiceImpl.class);

        }
    };

    public ITranService getTransMessage(PayTypeEnum payTypeEnum) {

        try {
            return tranServiceCache.get(payTypeEnum.getWay());
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

                    PayTypeEnum payTypeEnum= PayTypeEnum.getEnum(key);

                    log.info("加载注册bean=[{}]",payTypeEnum);

                    if (payTypeEnum==null){
                        return null;
                    }

                    if ( !servicesMapping.containsKey(payTypeEnum.getWay())) {
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

    private LoadingCache<Method, VerifyProd> verifyProdAnnoCache = CacheBuilder
            .newBuilder()
            .build(new CacheLoader<Method, VerifyProd>() {
                @Override
                public VerifyProd load(Method method) throws Exception {

                    return AnnotationUtils.findAnnotation(method, VerifyProd.class);
                }
            });


    public VerifyProd getVerifyProdAnnoCache(Method method) {

        try {
            return verifyProdAnnoCache.get(method);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }


    private LoadingCache<Method, VerifyProdType> verifyProdTypeAnnoCache = CacheBuilder
            .newBuilder()
            .build(new CacheLoader<Method, VerifyProdType>() {
                @Override
                public VerifyProdType load(Method method) throws Exception {

                    return AnnotationUtils.findAnnotation(method, VerifyProdType.class);
                }
            });


    public VerifyProdType verifyProdTypeAnnoCache(Method method) {

        try {
            return verifyProdTypeAnnoCache.get(method);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
