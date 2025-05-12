package com.emilio.servidor_multijugador.web.websocket;

import com.emilio.servidor_multijugador.web.websocket.WebHandlers.ChatWebHandler;
import com.emilio.servidor_multijugador.web.websocket.WebHandlers.PongWebHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new ChatWebHandler(), "/ws/chat")
                .setAllowedOrigins("*");
        registry.addHandler(new PongWebHandler(), "/ws/pong")
                .setAllowedOrigins("*");

    }

    public org.springframework.web.socket.WebSocketHandler chatWebSocketHandler() {
        return new ChatWebHandler();
    }
}