package com.distributed.transaction.service.core;

import com.distributed.transaction.module.gateway.domain.TccGatewayRecordEntity;
import com.distributed.transaction.module.gateway.repository.TccGatewayRecordRepository;
import com.distributed.transaction.module.gateway.vo.TccGatewayRecord;
import lombok.extern.log4j.Log4j2;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-24-下午 2:39
 */
@Log4j2
public abstract class AbstractTccGateWayRecord {

    @Autowired
    private TccGatewayRecordRepository tccGatewayRecordRepository;

    @Autowired
    private DozerBeanMapper mapper;

    @Transactional(rollbackFor = Exception.class)
    public TccGatewayRecord save(TccGatewayRecord vo) {

        log.info("保存网关流水记录");
        TccGatewayRecordEntity record = mapper.map(vo, TccGatewayRecordEntity.class);

        return mapper.map(tccGatewayRecordRepository.save(record), TccGatewayRecord.class);
    }

    @Transactional
    public TccGatewayRecord update(TccGatewayRecord vo) {

        log.info("更新网关流水记录");
        TccGatewayRecordEntity record =  tccGatewayRecordRepository.findByOrderNoAndPayKey(vo.getOrderNo(),vo.getPayKey());

//        record.setField1("测试22233333333333");
        return mapper.map(tccGatewayRecordRepository.save(record), TccGatewayRecord.class);
    }

}
