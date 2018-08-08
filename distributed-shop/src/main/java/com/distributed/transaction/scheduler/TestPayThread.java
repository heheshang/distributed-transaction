package com.distributed.transaction.scheduler;

import com.distributed.transaction.gateway.api.GateWayReq;
import com.distributed.transaction.gateway.api.GateWayRes;
import com.distributed.transaction.service.TranService;
import com.distributed.transaction.utils.BeanMapUtils;
import com.distributed.transaction.utils.MerchantApiUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.math.RandomUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-23-下午 3:27
 */
@Log4j2
public class TestPayThread implements Runnable {

    private String payKey;

    private String paySecret;

    private CountDownLatch latch;

    private TranService service;

    public TestPayThread(String payKey, String paySecret, CountDownLatch latch, TranService service) {

        this.payKey = payKey;
        this.paySecret = paySecret;
        this.latch = latch;
        this.service = service;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {

        log.info("执行测试网关支付线程号:[{}]", Thread.currentThread().getName());

        try {

            // 每个线程中生成100条充值记录
            for (int i = 0; i < 10; i++) {
                try {
                    int random = RandomUtils.nextInt(10);
                    long sleepNum = 10L * random;
                    Thread.sleep(sleepNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 创建支付订单（如果失败，需要商户重新发起）
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                // 生成商户订单号
                String merchantOrderNo = "pt" + Thread.currentThread().getId() + sdf.format(new Date()) + 000 + i;
                // 构建订单的请求参数
                TccGatewayRecordVo requestVo = getInitRequestMap(merchantOrderNo, payKey, paySecret);

                // 请求网关创建订单
                GateWayReq req = new GateWayReq();
                req.setT(requestVo);

                GateWayRes res = service.recharge(req);
                log.info("gateway 服务返回消息为:{}", res.toString());


                Map<String, String> resmap = (Map<String, String>) res.getMessage();

                String trxNo = resmap.get("trxNo");

                //模拟生成失败，跳出这次循环，继续下次操作。
                if (res.getMessage() == null || "".equals(res.getMessage().toString())) {
                    continue;
                }

                // 用户支付行为模拟（等待片刻）
                try {
                    Thread.sleep(200L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //模拟构建银行扣款成功结果通知
                Map<String, String> notifyMap = getNotifyRequestMap(trxNo);

                GateWayRes gateWayRes = service.notify(notifyMap);

                // String notifyResultStr = SimpleHttpUtils.httpGet("http://192.168.1.162:8082/roncoo-pay-web-gateway/scanPayNotify/notify/TEST_PAY_HTTP_CLIENT", notifyMap);
                log.info("支付完成返回,[{}]", gateWayRes.toString());
                latch.countDown();
            }
        } catch (Exception e) {
            log.error(e);
        } finally {
            latch.countDown();
        }
    }


    private static TccGatewayRecordVo getInitRequestMap(String merchantOrderNo, String payKey, String paySecret) {

        TccGatewayRecordVo vo = new TccGatewayRecordVo();
        // 订单金额 , 单位:元
        vo.setOrderPrice("10");
        //模拟网关支付
        vo.setPayWayCode("TEST_PAY_HTTP_CLIENT");
        // 订单编号
        vo.setOrderNo(merchantOrderNo);
        //订单日期
        Date orderDate = new Date();
        // 订单日期
        String orderDateStr = new SimpleDateFormat("yyyyMMdd").format(orderDate);
        vo.setOrderDate(orderDate);
        //订单时间
        Date orderTime = new Date();
        // 订单时间
        String orderTimeStr = new SimpleDateFormat("yyyyMMddHHmmss").format(orderTime);
        vo.setOrderTime(orderTime);
        vo.setPayKey(payKey);
        // 商品名称
        vo.setProductName("模拟支付网关支付产品");
        vo.setOrderIp("127.0.0.1");
        // 订单有效期
        vo.setOrderPeriod(30);
        // 页面通知返回url
        vo.setReturnUrl("http://192.168.1.162:8083/roncoo-pay-web-sample-shop/roncooPayNotify/notify");
        // 后台消息通知Url
        vo.setNotifyUrl("http://192.168.1.162:8083/roncoo-pay-web-sample-shop/roncooPayNotify/notify");
        // 支付备注


        ////////////扩展字段,选填,原值返回///////////
        // 扩展字段1
        vo.setField1("扩展字段1");
        vo.setField2("扩展字段2");
        vo.setField3("扩展字段3");
        vo.setField4("扩展字段4");
        vo.setField5("扩展字段5");


        /////签名及生成请求API的方法///
        Map<String, Object> paramMap = BeanMapUtils.beanToMap(vo);

        String sign = MerchantApiUtil.getSign(paramMap, paySecret);
        vo.setSign(sign);

        return vo;
    }

    /**
     * 模拟构建银行扣款成功结果通知
     *
     * @return
     */
    private static Map<String, String> getNotifyRequestMap(String trxNo) {

        Map<String, String> notifyMap = new HashMap<String, String>();
        notifyMap.put("result_code", "SUCCESS");
        String timeEnd = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        notifyMap.put("time_end", timeEnd);
        notifyMap.put("out_trade_no", trxNo);
        notifyMap.put("transaction_id", timeEnd);
        notifyMap.put("payWayCode", "TEST_PAY_HTTP_CLIENT");
        notifyMap.put("payTypeCode", "TEST_PAY_HTTP_CLIENT");
        notifyMap.put("trxNo", trxNo);
        notifyMap.put("bankOrderNo", trxNo);

        return notifyMap;
    }
}
