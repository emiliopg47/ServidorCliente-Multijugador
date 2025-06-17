package com.emilio.servidor_multijugador.web.websocket.handler;

import com.emilio.servidor_multijugador.Util.JsonUtils;
import com.emilio.servidor_multijugador.game.pong.modelos.GameState;
import com.emilio.servidor_multijugador.web.Mensajes.PongMove;
import com.fasterxml.jackson.databind.JsonNode;

public class DataHandler {
     public JsonNode chat(JsonNode data){
        return data;
    }

    public void movePaddle(JsonNode data, GameState gameState){
        PongMove movimiento = JsonUtils.fromJson(data.toString(), PongMove.class);
        gameState.actualizar(movimiento);
    }
}
