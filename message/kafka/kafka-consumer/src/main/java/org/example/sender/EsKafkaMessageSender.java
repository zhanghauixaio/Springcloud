package org.example.sender;

import org.example.channel.EsChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class EsKafkaMessageSender {
    @Autowired
    private EsChannel channel;

    /**
     * 消息发送到默认通道：缺省通道对应缺省主题
     * @param message
     */
    public void sendToDefaultChannel(String message){
        channel.sendEsDefaultMessage().send(MessageBuilder.withPayload(message).build());
    }

    /**
     * 消息发送到告警通道：告警通道对应告警主题
     * @param message
     */
    public void sendToAlarmChannel(String message){
        channel.sendEsAlarmMessage().send(MessageBuilder.withPayload(message).build());
    }
}