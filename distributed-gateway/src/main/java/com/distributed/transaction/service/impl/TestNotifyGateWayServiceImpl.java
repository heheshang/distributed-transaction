package com.distributed.transaction.service.impl;

import com.distributed.transaction.gateway.api.GateWayReq;
import com.distributed.transaction.gateway.api.GateWayRes;
import com.distributed.transaction.service.core.AbstractTccGateWayRecord;
import com.distributed.transaction.service.core.ITccGateWayRecordService;
import com.google.common.collect.Maps;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-01-下午 4:24
 */
@Log4j2
@Component("testNotifyGateWayService")
public class TestNotifyGateWayServiceImpl extends AbstractTccGateWayRecord implements ITccGateWayRecordService {

    @Override
    public GateWayRes handle(GateWayReq req) {

        Map<String, String> bankNotify = Maps.newHashMap();

        bankNotify.putAll((Map<? extends String, ? extends String>) req.getT());

        bankNotify.put("payWayCode", req.getPayWayEnum().name());

        int result = super.saveMessage(bankNotify);

        GateWayRes gateWayRes = new GateWayRes();

        gateWayRes.setSuccess(result == 1);

        return gateWayRes;
    }
}
