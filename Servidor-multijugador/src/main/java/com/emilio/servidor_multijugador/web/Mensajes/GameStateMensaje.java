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

    public double getPalaIzquierda() {
        return palaIzquierda;
    }

    public void setPalaIzquierda(double palaIzquierda) {
        this.palaIzquierda = palaIzquierda;
    }

    public double getPalaDerecha() {
        return palaDerecha;
    }

    public void setPalaDerecha(double palaDerecha) {
        this.palaDerecha = palaDerecha;
    }

    public double getxBola() {
        return xBola;
    }

    public void setxBola(double xBola) {
        this.xBola = xBola;
    }

    public double getyBola() {
        return yBola;
    }

    public void setyBola(double yBola) {
        this.yBola = yBola;
    }

    public int getMarcadorIzquierda() {
        return marcadorIzquierda;
    }

    public void setMarcadorIzquierda(int marcadorIzquierda) {
        this.marcadorIzquierda = marcadorIzquierda;
    }

    public int getMarcadorDerecha() {
        return marcadorDerecha;
    }

    public void setMarcadorDerecha(int marcadorDerecha) {
        this.marcadorDerecha = marcadorDerecha;
    }
}
