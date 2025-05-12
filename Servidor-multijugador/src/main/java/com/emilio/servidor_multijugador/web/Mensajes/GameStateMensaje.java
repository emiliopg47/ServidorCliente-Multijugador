package com.emilio.servidor_multijugador.web.Mensajes;

import com.emilio.servidor_multijugador.Util.JsonUtils;
import com.emilio.servidor_multijugador.game.pong.modelos.GameState;

public class GameStateMensaje {

    double palaIzquierda;
    double palaDerecha;

    double xBola;
    double yBola;

    int marcadorIzquierda;
    int marcadorDerecha;

    public GameStateMensaje(GameState gameState){
        this.palaIzquierda = gameState.getPalaIzquierda().getY();
        this.palaDerecha = gameState.getPalaDerecha().getY();

        this.xBola = gameState.getBola().getX();
        this.yBola = gameState.getBola().getY();

        this.marcadorIzquierda = gameState.getMarcadorIzq();
        this.marcadorDerecha = gameState.getMarcadorDrch();
    }

}
