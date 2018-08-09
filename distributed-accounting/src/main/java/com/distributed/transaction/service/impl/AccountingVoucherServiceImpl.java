package com.distributed.transaction.service.impl;

import com.distributed.transaction.accounting.api.AccountingReqT;
import com.distributed.transaction.accounting.api.AccountingResT;
import com.distributed.transaction.module.accounting.domain.AccountingVoucherEntity;
import com.distributed.transaction.module.accounting.repository.AccountingVoucherRepository;
import com.distributed.transaction.module.accounting.vo.AccountingVoucher;
import com.distributed.transaction.service.IAccountingVoucherService;
import com.distributed.transaction.utils.DateUtils;
import lombok.extern.log4j.Log4j2;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;

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

    @Autowired
    private EntityManager entityManagerAccounting;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AccountingResT createAccountingVoucher(AccountingReqT accountingReq) {

        log.info("创建快捷凭证服务开始【{}】", accountingReq);

        AccountingResT res = new AccountingResT();

        try {

            AccountingVoucher accountingVoucher = mapper.map(accountingReq.getParam(), AccountingVoucher.class);

            if (null == accountingReq.getParam()) {

                res.setSuccess(Boolean.FALSE);

            }

            AccountingVoucherEntity accountingVoucherEntity = accountingVoucherRepository.getByEntryTypeAndVoucherNoAndFromSystem(
                    accountingVoucher.getEntryType(), accountingVoucher.getVoucherNo(), accountingVoucher.getFromSystem());

            if (accountingVoucherEntity != null) {
                log.error("会计凭证数据已经存在【{}】", accountingVoucher.getVoucherNo());
                res.setSuccess(Boolean.FALSE);
            }


            AccountingVoucherEntity voucherEntity = new AccountingVoucherEntity();


            String requestNo = String.valueOf(entityManagerAccounting.createNativeQuery(" select FUN_SEQ('ACCOUNTING_REQUEST_NO_SEQ')").getSingleResult());
            voucherEntity.setEntryType(accountingVoucher.getEntryType());
            voucherEntity.setRequestNo(requestNo);
            voucherEntity.setFromSystem(accountingVoucher.getFromSystem());
            voucherEntity.setVoucherNo(accountingVoucher.getEntryType() + DateUtils.toString(new Date(), "yyyyMMdd") + requestNo);
            voucherEntity.setAccountingDate(new Date());
            voucherEntity.setBankChangeAmount(accountingVoucher.getBankChangeAmount());
            voucherEntity.setPayerAccountNo(accountingVoucher.getPayerAccountNo());
            voucherEntity.setReceiverAccountNo(accountingVoucher.getReceiverAccountNo());
            voucherEntity.setBankAccount(accountingVoucher.getBankAccount());
            voucherEntity.setBankChannelCode(accountingVoucher.getBankChannelCode());
            voucherEntity.setPayerChangeAmount(accountingVoucher.getPayerChangeAmount());
            voucherEntity.setReceiverChangeAmount(accountingVoucher.getReceiverChangeAmount());
            voucherEntity.setProfit(accountingVoucher.getProfit());
            voucherEntity.setIncome(accountingVoucher.getIncome());
            voucherEntity.setCost(accountingVoucher.getCost());
            voucherEntity.setRemark(accountingVoucher.getRemark());
            voucherEntity.setBankOrderNo(accountingVoucher.getBankOrderNo());
            voucherEntity.setPayerAccountType(accountingVoucher.getPayerAccountType());
            voucherEntity.setPayAmount(accountingVoucher.getPayAmount());
            voucherEntity.setReceiverAccountType(accountingVoucher.getReceiverAccountType());
            voucherEntity.setReceiverFee(accountingVoucher.getReceiverFee());
            voucherEntity.setPayerFee(accountingVoucher.getPayerFee());


            accountingVoucherRepository.save(voucherEntity);

            res.setSuccess(Boolean.TRUE);

        } catch (Exception e) {
            log.error("创建快捷凭证服务异常【{}】", e.getMessage());
            res.setSuccess(Boolean.FALSE);
        }

        return res;
    }
}
