package com.emilio.servidor_multijugador.web.websocket;

import com.emilio.servidor_multijugador.web.websocket.WebHandlers.ChatWebHandler;
import com.emilio.servidor_multijugador.web.websocket.WebHandlers.PongWebHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private ChatWebHandler chatWebHandler;

    @Autowired
    private PongWebHandler pongWebHandler;


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatWebHandler, "/ws/chat")
                .setAllowedOrigins("*");
        registry.addHandler(pongWebHandler, "/ws/pong")
                .setAllowedOrigins("*");

    }

    public org.springframework.web.socket.WebSocketHandler chatWebSocketHandler() {
        return new ChatWebHandler();
    }
}