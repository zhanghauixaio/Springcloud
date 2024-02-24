package org.example;

import org.example.channel.EsChannel;
import org.example.sender.EsKafkaMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableBinding(value = {EsChannel.class})
public class KafkaConsumerApplication implements CommandLineRunner {
    @Autowired
    private EsKafkaMessageSender esKafkaMessageSender;
    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        esKafkaMessageSender.sendToDefaultChannel("Hello World!");
    }
}
