package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Hello world!
 */
// @EnableRabbit
@EnableScheduling
@SpringBootApplication
public class RabbitmqClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitmqClientApplication.class, args);
    }
}
