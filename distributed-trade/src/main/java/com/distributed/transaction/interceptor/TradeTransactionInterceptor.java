package com.distributed.transaction.interceptor;

import com.distributed.transaction.BaseParam;
import com.distributed.transaction.annotations.VerifyProd;
import com.distributed.transaction.annotations.VerifyProdType;
import com.distributed.transaction.annotations.VerifyUser;
import com.distributed.transaction.checker.IChecker;
import com.distributed.transaction.enums.verify.UserVerifyEnum;
import com.distributed.transaction.register.TranServiceComponentRegister;
import com.distributed.transaction.register.VerifyServiceRegister;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 交易记录拦截器
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-26-下午 4:20
 */
@Component
@Log4j2
public class TradeTransactionInterceptor {

    @Autowired
    private TradeTranService tradeTranService;

    @Autowired
    private TranServiceComponentRegister register;

    @Autowired
    private VerifyServiceRegister verifyServiceRegister;


    public Object preHandleMethod(ProceedingJoinPoint pjp) throws Throwable {

        Object[] arg = pjp.getArgs();

        log.info("切面处理订单记录开始,处理请求为:[{}]", ToStringBuilder.reflectionToString(arg));

        Method[] methods = pjp.getTarget().getClass().getDeclaredMethods();

        for (Method method : methods) {

            VerifyUser verifyUserAnno = register.getVerifyUserAnnoCache(method);


            if (verifyUserAnno != null && verifyUserAnno.value() && verifyUserAnno.check().length >= 1) {
                log.info("进行用户权限验证 VerifyUser value=[{}],check=[{}]", verifyUserAnno.value(), verifyUserAnno.check());

                UserVerifyEnum[] verifyEnums = verifyUserAnno.check();

                for (UserVerifyEnum anEnum : verifyEnums) {

                    IChecker checker = verifyServiceRegister.tranServiceCache.asMap().get(anEnum.getAuthCode());

                    checker.exce((BaseParam) arg[0]);

                }


            }


            VerifyProdType verifyProdTypeAnno = register.verifyProdTypeAnnoCache(method);
            if (verifyProdTypeAnno != null && verifyProdTypeAnno.value() && verifyProdTypeAnno.type().length >= 1) {

                log.info("进用户产品类型验证 VerifyProd value=[{}],type=[{}]", verifyProdTypeAnno.value(), verifyProdTypeAnno.type());
            }

            VerifyProd verifyProductAnno = register.getVerifyProdAnnoCache(method);

            if (verifyProductAnno != null && verifyProductAnno.value() && verifyProductAnno.check().length >= 1) {

                log.info("进行用户产品权限验证 VerifyProd value=[{}],check=[{}]", verifyUserAnno.value(), verifyUserAnno.check());
            }

        }

        try {

            arg[0] = tradeTranService.bulidTradePaymentOrder((BaseParam) arg[0]);

            log.info("交易业务处理完成,继续执行业务逻辑");

            return pjp.proceed(arg);

        } catch (Exception e) {
            log.error("交易业务处理异常[{}]", e);
            throw e;
        }


    }


}
