package org.example.listener;

import com.rabbitmq.client.Channel;
import org.example.config.RabbitMQConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReceiveAndReplyCallback;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
@RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
public class RabbitMQListener {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitHandler
    // @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receive1(String message, Channel channel) throws IOException {
        // channel.
        System.out.println("receive message1: " + message);
        // channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
    @RabbitHandler
    // @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receive(@Payload(required = false) Message message, Channel channel) throws IOException {
        // channel.
        System.out.println("receive message: " + message);
        // channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
