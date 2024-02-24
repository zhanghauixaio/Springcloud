package org.example.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig implements SmartInstantiationAwareBeanPostProcessor,BeanPostProcessor {
    public static final String EXCHANGE_NAME = "spring-boot-exchange";
    public static final String DEAD_EXCHANGE_NAME = "spring-boot-exchange-dead";
    public static final String ROUTING_KEY = "spring-boot-routing-key";
    public static final String DEAD_ROUTING_KEY = "spring-boot-routing-key-dead";
    public static final String QUEUE_NAME = "spring-boot-queue";
    public static final String DEAD_QUEUE_NAME = "spring-boot-queue-dead";
    @Resource
    private RabbitAdmin rabbitAdmin;
    @Bean
    public Queue directQueue() {
        /**
         * 1、name:    队列名称
         * 2、durable: 是否持久化
         * 3、exclusive: 是否独享、排外的。如果设置为true，定义为排他队列。则只有创建者可以使用此队列。也就是private私有的。
         * 4、autoDelete: 是否自动删除。也就是临时队列。当最后一个消费者断开连接后，会自动删除。
         * */
        Map<String, Object> args = getDeadQueueArgs();
        args.put("x-max-length", 4);
        return new Queue(QUEUE_NAME, true, false, false, args);
    }
    @Bean
    public Queue deadQueue() {
        return new Queue(DEAD_QUEUE_NAME, true, false, false);
    }
    @Bean
    public Exchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME, true, false);
    }

    @Bean
    public Exchange deadExchange() {
        return new DirectExchange(DEAD_EXCHANGE_NAME, true, false);
    }
    // @Bean
    // public Binding directBinding() {
    //     return BindingBuilder.bind(directQueue())
    //             .to(directExchange())
    //             .with(ROUTING_KEY)
    //             .noargs();
    // }
    @Bean
    public Binding deadBinding() {
        return BindingBuilder.bind(deadQueue())
                .to(deadExchange())
                .with(DEAD_ROUTING_KEY)
                .noargs();
    }
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        // 只有设置为 true，spring 才会加载 RabbitAdmin 这个类
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        rabbitAdmin.declareExchange(directExchange());
        rabbitAdmin.declareQueue(directQueue());
        rabbitAdmin.declareExchange(deadExchange());
        rabbitAdmin.declareQueue(deadQueue());
        return bean;
    }

    private Map<String, Object> getDeadQueueArgs() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange", EXCHANGE_NAME + "-dead");
        map.put("x-dead-letter-routing-key", ROUTING_KEY + "-dead");
        return map;
    }
}
