package com.distributed.transaction.register;

import com.distributed.transaction.annotations.VerifyProd;
import com.distributed.transaction.annotations.VerifyProdType;
import com.distributed.transaction.annotations.VerifyUser;
import com.distributed.transaction.enums.PayTypeEnum;
import com.distributed.transaction.service.ITranService;
import com.distributed.transaction.service.banknotify.BankNotifyTestServiceImpl;
import com.distributed.transaction.service.recharge.AliPayTranRechargeServiceImpl;
import com.distributed.transaction.service.recharge.TestPayTranRechargeServiceImpl;
import com.distributed.transaction.service.recharge.WeChartPayTranRechargeServiceImpl;
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
public class TranServiceComponentRegister {


    private static Map<String, Class<? extends ITranService>> gatewayTradeTransServicesMapping = new ConcurrentHashMap<String, Class<? extends ITranService>>() {
        private static final long serialVersionUID = -8076694302460548904L;

        {
            put(PayTypeEnum.ALI_TEST.getWay(), AliPayTranRechargeServiceImpl.class);
            put(PayTypeEnum.WEIXIN_SCANPAY.getWay(), WeChartPayTranRechargeServiceImpl.class);
            put(PayTypeEnum.TEST_PAY_HTTP_CLIENT.getWay(), TestPayTranRechargeServiceImpl.class);

        }
    };

    private static Map<String, Class<? extends ITranService>> bankNotifyTransServicesMapping = new ConcurrentHashMap<String, Class<? extends ITranService>>() {
        private static final long serialVersionUID = -8076694302460548904L;

        {

            put(PayTypeEnum.TEST_PAY_HTTP_CLIENT.getWay(), BankNotifyTestServiceImpl.class);

        }
    };

    public ITranService getBankNotifyTransServiceService(PayTypeEnum payTypeEnum) {

        try {
            return bankNotifyTransServiceCache.get(payTypeEnum.getWay());
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }


    public ITranService getGatewayTradeTransService(PayTypeEnum payTypeEnum) {

        try {
            return gatewayTradeTransServiceCache.get(payTypeEnum.getWay());
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Autowired
    private ApplicationContext applicationContext;

    private LoadingCache<String, ITranService> gatewayTradeTransServiceCache = CacheBuilder
            .newBuilder()
            .maximumSize(20)
            .build(new CacheLoader<String, ITranService>() {
                @Override
                public ITranService load(String key) {

                    PayTypeEnum payTypeEnum = PayTypeEnum.getEnum(key);

                    log.info("加载注册gatewayTradeTransService bean=[{}]", payTypeEnum);

                    if (payTypeEnum == null) {
                        return null;
                    }

                    if (!gatewayTradeTransServicesMapping.containsKey(payTypeEnum.getWay())) {
                        return null;
                    }
                    return applicationContext.getBean(gatewayTradeTransServicesMapping.get(key));
                }
            });



    private LoadingCache<String, ITranService> bankNotifyTransServiceCache = CacheBuilder
            .newBuilder()
            .maximumSize(20)
            .build(new CacheLoader<String, ITranService>() {
                @Override
                public ITranService load(String key) {

                    PayTypeEnum payTypeEnum = PayTypeEnum.getEnum(key);

                    log.info("加载注册bankNotifyTransService bean=[{}]", payTypeEnum);

                    if (payTypeEnum == null) {
                        return null;
                    }

                    if (!bankNotifyTransServicesMapping.containsKey(payTypeEnum.getWay())) {
                        return null;
                    }
                    return applicationContext.getBean(bankNotifyTransServicesMapping.get(key));
                }
            });


    private LoadingCache<Method, VerifyUser> verifyUserAnnoCache = CacheBuilder
            .newBuilder()
            .build(new CacheLoader<Method, VerifyUser>() {
                @Override
                public VerifyUser load(Method method) {

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
                public VerifyProd load(Method method) {

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
                public VerifyProdType load(Method method) {

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
