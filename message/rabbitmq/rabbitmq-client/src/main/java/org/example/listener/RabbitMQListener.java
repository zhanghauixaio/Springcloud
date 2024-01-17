package org.example.listener;

import com.rabbitmq.client.Channel;
import org.example.config.RabbitMQConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.ReceiveAndReplyCallback;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class RabbitMQListener {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receive1(String message, Channel channel) throws IOException {
        System.out.println("DEAD_QUEUE receive message1: " + message);
        Message message1 = new Message(message.getBytes(), new MessageProperties());
        System.out.println("DEAD_QUEUE" + message1.getMessageProperties().getDeliveryTag());
        channel.basicAck(message1.getMessageProperties().getDeliveryTag(), false);
    }
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receive(@Payload(required = false) Message message, Channel channel) throws IOException {
        System.out.println("QUEUE receive message: " + new String(message.getBody()));
        System.out.println("getDeliveryTag " + message.getMessageProperties().getDeliveryTag());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
