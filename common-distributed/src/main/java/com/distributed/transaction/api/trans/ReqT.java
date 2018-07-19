package com.distributed.transaction.api.trans;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 请求
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-04-上午 10:25
 */
@Data
@ToString
public class ReqT<T extends BaseParam> implements Serializable {

    private T params;
}
