package org.example;

import org.example.scheduler.RabbitMQScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 */
@SpringBootApplication
public class RabbitmqProviderApplication implements CommandLineRunner {
    @Autowired
    private RabbitMQScheduler rabbitMQScheduler;
    public static void main(String[] args) {
        SpringApplication.run(RabbitmqProviderApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
      rabbitMQScheduler.send();
    }
}
