package com.distributed.transaction.config;

import com.distributed.transaction.enums.message.NotifyDestinationNameEnum;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.distributed.transaction.contants.RabbitMqConstants.EXCHANGES_BANK_NOTIFY;
import static com.distributed.transaction.contants.RabbitMqConstants.ROUTING_KEY;

/**
 * Created by Administrator on 2018/8/25.
 */
@Configuration
public class RabbitConfig {




    @Bean
    public Queue bankQueue() {

        // 是否持久化
        boolean durable = true;
        // 仅创建者可以使用的私有队列，断开后自动删除
        boolean exclusive = false;

        // 当所有消费客户端连接断开后，是否自动删除队列
        boolean autoDelete = false;

        return new Queue(NotifyDestinationNameEnum.BANK_NOTIFY.name(), durable, exclusive, autoDelete);
    }


    @Bean
    public TopicExchange exchange() {

        // 是否持久化
        boolean durable = true;
        // 当所有消费客户端连接断开后，是否自动删除队列
        boolean autoDelete = false;

        return new TopicExchange(EXCHANGES_BANK_NOTIFY, durable, autoDelete);

    }

    /**
     * 绑定路由
     */
    @Bean
    public Binding binding(Queue bankQueue, TopicExchange exchange) {

        return BindingBuilder.bind(bankQueue).to(exchange).with(ROUTING_KEY);

    }


    @Bean

    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueues(bankQueue());

//container.setMaxConcurrentConsumers(1);

//container.setConcurrentConsumers(1); 默认为1

//container.setExposeListenerChannel(true);

        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // 设置为手动，默认为 AUTO，如果设置了手动应答 basicack，就要设置manual

        return container;

    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setHost("192.168.111.103");
        cachingConnectionFactory.setUsername("springboot");
        cachingConnectionFactory.setPassword("123456");
        cachingConnectionFactory.setPort(5672);

        return cachingConnectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());

    }
}
