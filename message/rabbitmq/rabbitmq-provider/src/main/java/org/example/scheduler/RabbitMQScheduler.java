package org.example.scheduler;

import com.rabbitmq.client.Channel;
import org.example.config.RabbitMQConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Configuration
public class RabbitMQScheduler {
    @Resource
    private RabbitTemplate rabbitTemplate;
    // 消息发送确认回调
    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (Boolean.FALSE.equals(ack)) {
                    System.out.println("消息发送成功: " + cause);
                }
            }
        });
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            System.out.println("消息发送失败: " + message.getMessageProperties().getCorrelationId());
            System.out.println("exchange: " + exchange + ", routingKey: " + routingKey);
        });
    }

    public void send() throws InterruptedException, IOException {
        for (int i = 0; i < 10; i++) {
            MessageProperties properties = new MessageProperties();
            properties.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            // Message message = MessageBuilder.withBody("Hello World".getBytes())
            //         .andProperties(properties)
            //         .build();
            String message = "jahghg";
            // Channel channel = rabbitTemplate.getConnectionFactory().createConnection().createChannel(false);
            // // 开启发送确认
            // channel.confirmSelect();
            // // 发送成功确认
            // channel.waitForConfirms();
            // // 开启事务
            // rabbitTemplate.setChannelTransacted();
            CorrelationData data = new CorrelationData(String.valueOf(i));
            if (i < 4) {
                rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, message, data);
                TimeUnit.SECONDS.sleep(2L);
            }else {
                rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY+"hahv", message, data);
            }
        }
    }
}
