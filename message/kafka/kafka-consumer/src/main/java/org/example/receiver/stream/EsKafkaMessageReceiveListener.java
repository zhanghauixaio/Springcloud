package org.example.receiver.stream;

import lombok.extern.slf4j.Slf4j;
import org.example.channel.EsChannel;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

import java.time.LocalDateTime;
@Slf4j
@EnableBinding(value = EsChannel.class)
public class EsKafkaMessageReceiveListener {

    /**
     * 从缺省通道接收消息
     * @param message
     */
    @StreamListener(EsChannel.ES_DEFAULT_INPUT)
    public void receive(Message<String> message){
        log.info("{}订阅告警消息：通道 = es_default_input，data = {}", LocalDateTime.now(), message);
    }

    /**
     * 从告警通道接收消息
     * @param message
     */
    @StreamListener(EsChannel.ES_ALARM_INPUT)
    public void receiveAlarm(Message<String> message){
        System.out.println("订阅告警消息：" + message);
        log.info("{}订阅告警消息：通道 = es_alarm_input，data = {}", LocalDateTime.now(), message);
    }
}