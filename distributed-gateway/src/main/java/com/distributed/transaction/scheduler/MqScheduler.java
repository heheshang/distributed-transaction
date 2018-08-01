package com.distributed.transaction.scheduler;

import com.distributed.transaction.gateway.api.GateWayReq;
import com.distributed.transaction.service.core.ITccGateWayRecordService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-01-下午 5:29
 */
@EnableScheduling
@Component
public class MqScheduler {


    @Autowired
    private ITccGateWayRecordService testNotifyGateWayService;

    @Scheduled(cron = "0 0/1 * * * ? " )
    public void testBankNotify() {

        GateWayReq gateWayReq = new GateWayReq();

        Map<String, String> notifyMap = Maps.newHashMap();

        notifyMap.put("result_code", "success");

        notifyMap.put("bank_no", String.valueOf(System.currentTimeMillis()));

        gateWayReq.setT(notifyMap);

        testNotifyGateWayService.handle(gateWayReq);
    }
}
