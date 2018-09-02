package com.distributed.transation.config;

import com.distributed.transation.scheduled.testmq.RabbitMqMessage;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created by Administrator on 2018/8/25.
 */
@Configuration
public class RabbitConfig {


    @Bean(name = "hospSyncConnectionFactory")
    @Primary
    public ConnectionFactory hospSyncConnectionFactory(
            @Value("${spring.rabbitmq.hospSync.host}") String host,
            @Value("${spring.rabbitmq.hospSync.port}") int port,
            @Value("${spring.rabbitmq.hospSync.username}") String username,
            @Value("${spring.rabbitmq.hospSync.password}") String password,
            @Value("${spring.rabbitmq.hospSync.virtual-host}") String virtualHost) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);

        return connectionFactory;
    }

    @Bean(name = "hospSyncRabbitAdmin")
    @Primary
    RabbitAdmin rabbitAdmin(@Qualifier("hospSyncConnectionFactory") ConnectionFactory hospSyncConnectionFactory) {
        RabbitAdmin admin = new RabbitAdmin(hospSyncConnectionFactory);
        return admin;
    }

//    @Bean("containerPrimary")
//    public SimpleMessageListenerContainer containerPrimary(
//            @Qualifier("hospSyncConnectionFactory") ConnectionFactory hospSyncConnectionFactory,
//            @Qualifier("hospSyncRabbitAdmin") RabbitAdmin hospSyncRabbitAdmin) {
//
//        SimpleMessageListenerContainer containerPrimary = new SimpleMessageListenerContainer();
//        containerPrimary.setConnectionFactory(hospSyncConnectionFactory);
//        containerPrimary.setQueues(testHospitalAddQueue(hospSyncRabbitAdmin), testHospitalUpdateQueue(hospSyncRabbitAdmin), testHospitalDeleteQueue(hospSyncRabbitAdmin));
//        containerPrimary.setRabbitAdmin(hospSyncRabbitAdmin);
//
//        //container.setMaxConcurrentConsumers(1);
//        //container.setConcurrentConsumers(1); 默认为1
//        //container.setExposeListenerChannel(true);
//        containerPrimary.setAcknowledgeMode(AcknowledgeMode.MANUAL); // 设置为手动，默认为 AUTO，如果设置了手动应答 basicack，就要设置manual
//        return containerPrimary;
//
//    }

    /**
     * queue 队列声明
     *
     * @param hospSyncRabbitAdmin
     * @return
     */
    @Bean(name = "testHospitalAddQueue")
    public Queue testHospitalAddQueue(@Qualifier("hospSyncRabbitAdmin") RabbitAdmin hospSyncRabbitAdmin) {
        Queue queue = new Queue("test.hospital.add", true, false, false);
        queue.setAdminsThatShouldDeclare(hospSyncRabbitAdmin);
        return queue;
    }

    @Bean(name = "testHospitalUpdateQueue")
    public Queue testHospitalUpdateQueue(@Qualifier("hospSyncRabbitAdmin") RabbitAdmin hospSyncRabbitAdmin) {
        Queue queue = new Queue("test.hospital.update", true, false, false);
        queue.setAdminsThatShouldDeclare(hospSyncRabbitAdmin);
        return queue;
    }

    @Bean(name = "testHospitalDeleteQueue")
    public Queue testHospitalDeleteQueue(@Qualifier("hospSyncRabbitAdmin") RabbitAdmin hospSyncRabbitAdmin) {
        Queue queue = new Queue("test.hospital.delete", true, false, false);
        queue.setAdminsThatShouldDeclare(hospSyncRabbitAdmin);
        return queue;
    }

    /**
     * exchange queue binging key 绑定
     *
     * @return
     */
    @Bean
    TopicExchange topicExchange(@Qualifier("hospSyncRabbitAdmin") RabbitAdmin hospSyncRabbitAdmin) {
        TopicExchange topicExchange = new TopicExchange("topic.message", true, false);
        topicExchange.setAdminsThatShouldDeclare(hospSyncRabbitAdmin);
        return topicExchange;
    }


    /**
     * 将队列topic.message与exchange绑定，binding_key为topic.message,就是完全匹配
     *
     * @param testHospitalAddQueue
     * @param topicExchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessageAdd(@Qualifier("testHospitalAddQueue") Queue testHospitalAddQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(testHospitalAddQueue).to(topicExchange).with("topic.message.routekey");
    }

    @Bean
    Binding bindingExchangeMessageUpdate(@Qualifier("testHospitalUpdateQueue") Queue testHospitalUpdateQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(testHospitalUpdateQueue).to(topicExchange).with("topic.message.routekey");
    }

    @Bean
    Binding bindingExchangeMessageDelete(@Qualifier("testHospitalDeleteQueue") Queue testHospitalDeleteQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(testHospitalDeleteQueue).to(topicExchange).with("topic.message.routekey");
    }

    @Bean(name = "hospSyncRabbitTemplate")
    @Primary
    public RabbitTemplate firstRabbitTemplate(@Qualifier("hospSyncConnectionFactory") ConnectionFactory connectionFactory) {
        RabbitTemplate hospSyncRabbitTemplate = new RabbitTemplate(connectionFactory);
        //使用外部事物
        //ydtRabbitTemplate.setChannelTransacted(true);
        return hospSyncRabbitTemplate;
    }

    @Bean(name = "hospSyncContainerFactory")
    @Primary
    public SimpleRabbitListenerContainerFactory containerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            @Qualifier("hospSyncConnectionFactory") ConnectionFactory connectionFactory) {

        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);

        return factory;
    }


    @Bean(name = "hPayConnectionFactory")
    public ConnectionFactory hPayConnectionFactory(
            @Value("${spring.rabbitmq.pay.host}") String host,
            @Value("${spring.rabbitmq.pay.port}") int port,
            @Value("${spring.rabbitmq.pay.username}") String username,
            @Value("${spring.rabbitmq.pay.password}") String password,
            @Value("${spring.rabbitmq.pay.virtual-host}") String virtualHost) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        return connectionFactory;
    }


    @Bean(name = "hPayRabbitAdmin")
    RabbitAdmin hPayRabbitAdmin(@Qualifier("hPayConnectionFactory") ConnectionFactory hPayConnectionFactory) {
        RabbitAdmin admin = new RabbitAdmin(hPayConnectionFactory);
        return admin;
    }


    @Bean(name = "demoAddQueue")
    public Queue demoAddQueue(@Qualifier("hPayRabbitAdmin") RabbitAdmin hPayRabbitAdmin) {
        Queue queue = new Queue("demo.add", true, false, false);
        queue.setAdminsThatShouldDeclare(hPayRabbitAdmin);
        return queue;
    }

    @Bean(name = "demoUpdateQueue")
    public Queue demoUpdateQueue(@Qualifier("hPayRabbitAdmin") RabbitAdmin hPayRabbitAdmin) {
        Queue queue = new Queue("demo.update", true, false, false);
        queue.setAdminsThatShouldDeclare(hPayRabbitAdmin);
        return queue;
    }

    @Bean(name = "tradeExchange")
    TopicExchange tradeExchange(@Qualifier("hPayRabbitAdmin") RabbitAdmin hPayRabbitAdmin) {
        TopicExchange fanoutExchange = new TopicExchange("trade.exchange", true, false);
        fanoutExchange.setAdminsThatShouldDeclare(hPayRabbitAdmin);
        return fanoutExchange;
    }


    @Bean
    Binding bindingExchangeA(@Qualifier("demoAddQueue") Queue demoAddQueue, @Qualifier("tradeExchange") TopicExchange tradeExchange) {
        return BindingBuilder.bind(demoAddQueue).to(tradeExchange).with("trade.exchange.routeKey");
    }

    @Bean
    Binding bindingExchangeB(@Qualifier("demoUpdateQueue") Queue demoUpdateQueue, @Qualifier("tradeExchange") TopicExchange tradeExchange) {
        return BindingBuilder.bind(demoUpdateQueue).to(tradeExchange).with("trade.exchange.routeKey");
    }

    @Bean(name = "hPayRabbitTemplate")
    public RabbitTemplate secondRabbitTemplate(
            @Qualifier("hPayConnectionFactory") ConnectionFactory connectionFactory) {

        RabbitTemplate hPayRabbitTemplate = new RabbitTemplate(connectionFactory);
        //使用外部事物
        //lpzRabbitTemplate.setChannelTransacted(true);
        return hPayRabbitTemplate;
    }


    @Bean(name = "hPayContainerFactory")
    public SimpleRabbitListenerContainerFactory hPayFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            @Qualifier("hPayConnectionFactory") ConnectionFactory hPayConnectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, hPayConnectionFactory);
        return factory;
    }

//    @Bean("hPaycontainer")
//    public SimpleMessageListenerContainer hPaycontainer(
//            @Qualifier("hPayConnectionFactory") ConnectionFactory hPayConnectionFactor,
//            @Qualifier("hPayRabbitAdmin") RabbitAdmin hPayRabbitAdmin) {
//
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(hPayConnectionFactor);
//        container.setQueues(demoUpdateQueue(hPayRabbitAdmin), demoAddQueue(hPayRabbitAdmin));
//        container.setRabbitAdmin(hPayRabbitAdmin);
////        container.setChannelAwareMessageListener(new RabbitMqMessage());
//        //container.setMaxConcurrentConsumers(1);
//        //container.setConcurrentConsumers(1); 默认为1
//        //container.setExposeListenerChannel(true);
//        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // 设置为手动，默认为 AUTO，如果设置了手动应答 basicack，就要设置manual
//        return container;
//
//    }
//    @Bean
//    TopicExchange demoExchange() {
//        return new TopicExchange("topic.#");
//    }


}
