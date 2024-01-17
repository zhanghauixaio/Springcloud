package org.example;

import org.example.scheduler.RabbitMQScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Hello world!
 */
@EnableScheduling
@SpringBootApplication
public class RabbitmqProviderApplication {
    @Autowired
    private RabbitMQScheduler rabbitMQScheduler;
    public static void main(String[] args) {
        SpringApplication.run(RabbitmqProviderApplication.class, args);
    }

}
