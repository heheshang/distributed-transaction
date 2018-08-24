package com.distributed.transaction.message;

import com.distributed.transaction.message.api.MessageReqT;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 消息服务接口
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-08-上午 9:45
 */
@FeignClient("DISTRIBUTED-MESSAGE")
public interface BaseMessageManagerApi {

    /**
     * 发送待确认消息
     * @param messageReq 消息请求对象
     */
    @RequestMapping(value = "/message/confirm/send", method = RequestMethod.POST)
    void confirmAndSendMessage(MessageReqT messageReq);

}
