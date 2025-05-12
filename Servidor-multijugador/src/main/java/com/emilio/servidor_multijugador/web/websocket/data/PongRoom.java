package com.emilio.servidor_multijugador.web.websocket.data;

import com.emilio.servidor_multijugador.game.pong.Loop.GameLoop;
import com.emilio.servidor_multijugador.game.pong.modelos.GameState;

public class PongRoom extends Room{

    GameLoop gameLoop;

    public PongRoom(String id) {
        super(id);
    }

    public GameLoop getGameLoop() {
        return gameLoop;
    }

    public void setGameLoop(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }

    public GameState getGameState() {
        return gameLoop.getEstado();
    }
}
