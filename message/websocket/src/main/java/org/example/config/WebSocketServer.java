package org.example.config;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

@Component
@ServerEndpoint("/websocket")
public class WebSocketServer {
    @OnOpen
    public void onOpen(javax.websocket.Session session) {
        System.out.println("WebSocketServer onOpen");
    }
    @OnMessage
    public void onMessage(String message) {
        System.out.println("message: " + message);
    }
    @OnClose
    public void onClose(javax.websocket.Session session) {
        System.out.println("WebSocketServer onClose");
    }
}
