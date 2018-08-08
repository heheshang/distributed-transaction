package com.distributed.transaction.service.impl;

import com.distributed.transaction.service.IAccountingVoucherService;
import com.distributed.transaction.accounting.api.AccountingReqT;
import com.distributed.transaction.accounting.api.AccountingResT;
import com.distributed.transaction.module.accounting.domain.AccountingVoucherEntity;
import com.distributed.transaction.module.accounting.repository.AccountingVoucherRepository;
import com.distributed.transaction.module.accounting.vo.AccountingVoucher;
import lombok.extern.log4j.Log4j2;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-08-下午 2:58
 */
@Service("accountingVoucherService")
@Log4j2
public class AccountingVoucherServiceImpl implements IAccountingVoucherService {

    @Autowired
    private AccountingVoucherRepository accountingVoucherRepository;

    @Autowired
    private DozerBeanMapper mapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AccountingResT createAccountingVoucher(AccountingReqT accountingReq) {

        log.info("创建快捷凭证服务开始【{}】", accountingReq);

        AccountingResT res = new AccountingResT();

        try {

            AccountingVoucher accountingVoucher = (AccountingVoucher) accountingReq.getParam();

            if (null == accountingReq.getParam()) {

                res.setSuccess(Boolean.FALSE);

            }

            AccountingVoucherEntity voucherEntity = mapper.map(accountingVoucher, AccountingVoucherEntity.class);

            accountingVoucherRepository.save(voucherEntity);


        } catch (Exception e) {
            log.error("创建快捷凭证服务异常【{}】", e);
            res.setSuccess(Boolean.FALSE);
        }

        return res;
    }
}
