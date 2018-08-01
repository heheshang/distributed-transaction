package com.distributed.transaction.service.recharge;

import com.distributed.transaction.annotations.TradeTransType;
import com.distributed.transaction.annotations.VerifyProd;
import com.distributed.transaction.annotations.VerifyProdType;
import com.distributed.transaction.annotations.VerifyUser;
import com.distributed.transaction.enums.PayTypeEnum;
import com.distributed.transaction.enums.trade.TradeStatusEnum;
import com.distributed.transaction.service.ITranService;
import com.distributed.transaction.trade.api.recharge.RechargeMessage;
import com.distributed.transaction.trade.api.recharge.RechargeParam;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import static com.distributed.transaction.enums.product.ProductTypeEnum.RECHARGE;
import static com.distributed.transaction.enums.verify.ProductVerifyEnum.FEE;
import static com.distributed.transaction.enums.verify.ProductVerifyEnum.PERMISSION;
import static com.distributed.transaction.enums.verify.UserVerifyEnum.USER_AUTH;
import static com.distributed.transaction.enums.verify.UserVerifyEnum.USER_MAIL;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-25-下午 3:04
 */
@TradeTransType(value = PayTypeEnum.TEST_PAY_HTTP_CLIENT)
@Log4j2
public class TestPayTranServiceImpl implements ITranService<RechargeParam, RechargeMessage> {

    @Autowired
    private DozerBeanMapper mapper;

    /**
     * 方法上使用注解,通过拦截注解,
     * 找出所需要处理的校验器进行方法校验,
     * 多个校验器使用迭代器的形式进行迭代校验
     * 注解中的值,代表的校验器使用 拦截注解类的方法进行拦截
     *
     * @param param
     * @return
     */
    @VerifyUser(check = {USER_AUTH, USER_MAIL})
    @VerifyProdType(type = {RECHARGE})
    @VerifyProd(check = {FEE, PERMISSION})
    @Override
    public RechargeMessage handle(RechargeParam param) {

        log.info("虚拟充值测试服务执行param=[{}]", param.toString());

        RechargeMessage message = new RechargeMessage();

        if (StringUtils.equals(param.getStatus(), TradeStatusEnum.WAITING_PAYMENT.name())) {

            message =mapper.map(param, RechargeMessage.class);

            log.info("虚拟充值测试服务执行完成返回,message=[{}]", param.toString());
        }

        return message;
    }


}
