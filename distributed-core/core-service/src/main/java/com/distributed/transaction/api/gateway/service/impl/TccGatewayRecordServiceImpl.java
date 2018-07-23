package com.distributed.transaction.api.gateway.service.impl;

import com.distributed.transaction.api.gateway.domain.TccGatewayRecord;
import com.distributed.transaction.api.gateway.repository.TccGatewayRecordRepository;
import com.distributed.transaction.api.gateway.service.ITccGatewayRecordService;
import com.distributed.transaction.api.gateway.vo.TccGatewayRecordVo;
import lombok.extern.log4j.Log4j2;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-20-下午 2:02
 */
@Service("tccGatewayRecordService")
@Log4j2
public class TccGatewayRecordServiceImpl implements ITccGatewayRecordService {

    @Autowired
    private TccGatewayRecordRepository tccGatewayRecordRepository;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    @Transactional
    public TccGatewayRecordVo save(TccGatewayRecordVo vo) {

        log.info("保存网关流水记录");
        TccGatewayRecord record = mapper.map(vo, TccGatewayRecord.class);

        return mapper.map(tccGatewayRecordRepository.save(record),TccGatewayRecordVo.class);
    }
}
