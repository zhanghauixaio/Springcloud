package org.example.receiver;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
public class EsChannelListener {
    private KafkaTemplate kafkaTemplate;
    private KafkaConsumer kafkaConsumer;
    @KafkaListener(topicPartitions = @TopicPartition(topic = "test", partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "0")))
    public void receive(@Payload String payload, Message message){
        System.out.println("getPayload: " + message.getPayload());
        System.out.println("payload: " + payload);
        // new ConcurrentKafkaListenerContainerFactory<>().getContainerProperties().setConsumerRebalanceListener();
    }
    @MessageMapping()
    public void receive(@Payload String message){
        System.out.println(message);
    }
}
