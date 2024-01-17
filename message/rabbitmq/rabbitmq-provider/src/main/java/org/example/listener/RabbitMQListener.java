package org.example.listener;

import org.example.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

// @Component
// @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
// public class RabbitMQListener {
//
//     @RabbitHandler
//     public void receive(String message) {
//         System.out.println("receive message: " + message);
//     }
// }
