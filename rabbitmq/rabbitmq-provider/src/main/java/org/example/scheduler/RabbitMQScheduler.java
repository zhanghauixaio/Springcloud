package org.example.scheduler;

import org.example.config.RabbitMQConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RabbitMQScheduler {
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Scheduled(fixedRate = 5000, initialDelay = 1000)
    public void send() throws InterruptedException {
        // TimeUnit.MICROSECONDS.sleep(1000L);
        // MessageProperties properties = new MessageProperties();
        // properties.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        // Message message = MessageBuilder.withBody("Hello World".getBytes())
        //         .andProperties(properties)
        //         .build();
        // rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, message);
        System.out.println("发送消息: " + System.currentTimeMillis());
    }
}
