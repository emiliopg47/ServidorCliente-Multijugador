package com.emilio.servidor_multijugador.web.websocket.handler;

import com.emilio.servidor_multijugador.Util.JsonUtils;
import com.emilio.servidor_multijugador.game.pong.modelos.GameState;
import com.emilio.servidor_multijugador.web.Mensajes.PongMove;
import com.fasterxml.jackson.databind.JsonNode;

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

    public void movePaddle(JsonNode data, GameState gameState){
        PongMove movimiento = JsonUtils.fromJson(data.toString(), PongMove.class);
        gameState.actualizar(movimiento);
    }
}
