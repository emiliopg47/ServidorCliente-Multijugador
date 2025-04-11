package com.emilio.servidor_multijugador.web.websocket.handler;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.WebSocketSession;

public class DataHandler {
    public JsonNode move(JsonNode data) {
        int fromX = data.get("from").get("x").asInt();
        int fromY = data.get("from").get("y").asInt();
        int toX = data.get("to").get("x").asInt();
        int toY = data.get("to").get("y").asInt();
        String piece = data.get("piece").asText();
        String color = data.get("color").asText();

        // Validar y reenviar el movimiento
        return data;
    }

    public JsonNode chat(JsonNode data){
        return data;
    }
}
