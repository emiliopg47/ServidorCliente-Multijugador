package com.emilio.servidor_multijugador.game.pong.Loop;

import com.emilio.servidor_multijugador.Util.CONFIG;
import com.emilio.servidor_multijugador.game.pong.modelos.GameState;
import org.springframework.scheduling.annotation.Scheduled;

public class GameLoop {
  private final GameState estado;

    public GameLoop(GameState estado) {
        this.estado = estado;
    }


    @Scheduled(fixedRate = 16) // 60 FPS
    public void loop() {
        // 1. Actualizar lógica del juego
        estado.actualizar();
        // 2. Comprobar colisiones
        estado.comprobarColisiones();

        // 3. Comprobar si hay un ganador

        // 4. Enviar el estado del juego a los jugadores

    }

    public GameState getEstado() {
        return estado;
    }
}
