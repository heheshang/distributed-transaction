package com.distributed.transation.scheduled.testmq;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/9/1.
 */
@Component
@Log4j2
public class RabbitMqMessage  {




    @RabbitListener(
            //1.rabbitAdmin:RabbitAdmin名称
            admin = "hospSyncRabbitAdmin",
            bindings = {@QueueBinding(
                    //1.test.demo.send:队列名,2.true:是否长期有效,3.false:是否自动删除
                    value = @Queue(value = "test.hospital.add", durable = "true", autoDelete = "false"),
                    //1.default.topic交换器名称(默认值),2.true:是否长期有效,3.topic:类型是topic
                    exchange = @Exchange(value = "topic.message", durable = "true", type = "topic"),
                    //test2.send:路由的名称,ProducerConfig 里面 绑定的路由名称(xxxx.to(exchange).with("test2.send")))
                    key = "topic.message.routekey"),
                    @QueueBinding(
                            //1.test.demo.send:队列名,2.true:是否长期有效,3.false:是否自动删除
                            value = @Queue(value = "test.hospital.update", durable = "true", autoDelete = "false"),
                            //1.default.topic交换器名称(默认值),2.true:是否长期有效,3.topic:类型是topic
                            exchange = @Exchange(value = "topic.message", durable = "true", type = "topic"),
                            //test2.send:路由的名称,ProducerConfig 里面 绑定的路由名称(xxxx.to(exchange).with("test2.send")))
                            key = "topic.message.routekey"),
                    @QueueBinding(
                            //1.test.demo.send:队列名,2.true:是否长期有效,3.false:是否自动删除
                            value = @Queue(value = "test.hospital.delete", durable = "true", autoDelete = "false"),
                            //1.default.topic交换器名称(默认值),2.true:是否长期有效,3.topic:类型是topic
                            exchange = @Exchange(value = "topic.message", durable = "true", type = "topic"),
                            //test2.send:路由的名称,ProducerConfig 里面 绑定的路由名称(xxxx.to(exchange).with("test2.send")))
                            key = "topic.message.routekey")

            },containerFactory = "hospSyncContainerFactory"
    )
    public void hospitalAdd(String message) {
        log.info("Reciver data: {}",message);
    }

    @RabbitListener(
            //1.rabbitAdmin:RabbitAdmin名称
            admin = "hPayRabbitAdmin",
            bindings = {@QueueBinding(
                    //1.test.demo.send:队列名,2.true:是否长期有效,3.false:是否自动删除
                    value = @Queue(value = "demo.add", durable = "true", autoDelete = "false"),
                    //1.default.topic交换器名称(默认值),2.true:是否长期有效,3.topic:类型是topic
                    exchange = @Exchange(value = "trade.exchange", durable = "true", type = "topic"),
                    //test2.send:路由的名称,ProducerConfig 里面 绑定的路由名称(xxxx.to(exchange).with("test2.send")))
                    key = "trade.exchange.routeKey"),
                    @QueueBinding(
                            //1.test.demo.send:队列名,2.true:是否长期有效,3.false:是否自动删除
                            value = @Queue(value = "demo.update", durable = "true", autoDelete = "false"),
                            //1.default.topic交换器名称(默认值),2.true:是否长期有效,3.topic:类型是topic
                            exchange = @Exchange(value = "trade.exchange", durable = "true", type = "topic"),
                            //test2.send:路由的名称,ProducerConfig 里面 绑定的路由名称(xxxx.to(exchange).with("test2.send")))
                            key = "trade.exchange.routeKey")

            },containerFactory = "hPayContainerFactory"
    )
    public void hPayAdd( String data) {
        log.info("Reciver data: {}",data);
    }


}
