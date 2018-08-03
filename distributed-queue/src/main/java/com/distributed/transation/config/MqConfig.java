package com.distributed.transation.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.JMSException;
import javax.jms.Queue;

import static com.distributed.transaction.enums.message.NotifyDestinationNameEnum.BANK_NOTIFY;

/**
 * active mq 配置
 *
 * @author ssk www.distributed.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-04-下午 3:55
 */
@Configuration
@EnableJms
public class MqConfig {



    @Bean
    public Queue queue() {

        return new ActiveMQQueue(BANK_NOTIFY.name());
    }


    @Bean
    public RedeliveryPolicy redeliveryPolicy() {

        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        //是否在每次尝试重新发送失败后,增长这个等待时间
        redeliveryPolicy.setUseExponentialBackOff(true);
        //重发次数,默认为6次   这里设置为10次
        redeliveryPolicy.setMaximumRedeliveries(10);
        //重发时间间隔,默认为1秒
        redeliveryPolicy.setInitialRedeliveryDelay(1);
        //第一次失败后重新发送之前等待500毫秒,第二次失败再等待500 * 2毫秒,这里的2就是value
        redeliveryPolicy.setBackOffMultiplier(2);
        //是否避免消息碰撞
        redeliveryPolicy.setUseCollisionAvoidance(false);
        //设置重发最大拖延时间-1 表示没有拖延只有UseExponentialBackOff(true)为true时生效
        redeliveryPolicy.setMaximumRedeliveryDelay(-1);
        return redeliveryPolicy;

    }

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {

        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();

        factory.setRedeliveryPolicy(redeliveryPolicy());


        return factory;
    }

    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ActiveMQConnectionFactory connectionFactory) {

        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(connectionFactory);

        return bean;
    }

    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ActiveMQConnectionFactory connectionFactory) {

        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        //TODO 客户端签收模式
//        bean.setSessionAcknowledgeMode(4);
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }

    /**
     * AUTO_ACKNOWLEDGE = 1    自动确认
     * CLIENT_ACKNOWLEDGE = 2    客户端手动确认
     * DUPS_OK_ACKNOWLEDGE = 3    自动批量确认
     * SESSION_TRANSACTED = 0    事务提交并确认
     * INDIVIDUAL_ACKNOWLEDGE = 4    单条消息确认 activemq 独有
     *
     * @param connectionFactory
     * @param queue
     * @return
     */
    @Bean
    public JmsTemplate jmsTemplate(ActiveMQConnectionFactory connectionFactory, Queue queue) {

        JmsTemplate jmsTemplate = new JmsTemplate();

        //进行持久化配置 1表示非持久化，2表示持久化
        jmsTemplate.setDeliveryMode(2);

        jmsTemplate.setConnectionFactory(connectionFactory);

        //此处可不设置默认，在发送消息时也可设置队列
        jmsTemplate.setDefaultDestination(queue);

        //客户端签收模式
        jmsTemplate.setSessionAcknowledgeMode(4);

        return jmsTemplate;
    }

    @Bean
    public JmsMessagingTemplate jmsMessagingTemplate(ActiveMQConnectionFactory connectionFactory) {

        return new JmsMessagingTemplate(connectionFactory);
    }


    /**
     * 定义一个消息监听器连接工厂，这里定义的是点对点模式的监听器连接工厂
     */
    @Bean(name = "bankQueueJmsListener")
    public DefaultJmsListenerContainerFactory jmsQueueListenerContainerFactory(ActiveMQConnectionFactory connectionFactory) {

        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        //设置连接数
        factory.setConcurrency("1-10");
        //重连间隔时间
        factory.setRecoveryInterval(1000L);
        // TODO 客户端签收模式
        factory.setSessionAcknowledgeMode(4);
        return factory;
    }


}
