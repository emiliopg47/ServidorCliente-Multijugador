package com.emilio.servidor_multijugador.game.pong.modelos;

import com.emilio.servidor_multijugador.Util.APP_VARIABLES;
import com.emilio.servidor_multijugador.web.Mensajes.PongMove;

import static com.emilio.servidor_multijugador.Util.APP_VARIABLES.altoPala;
import static java.lang.Math.abs;

public class GameState {

    private int marcadorIzq;
    private int marcadorDrch;
    private Bola bola;
    private Pala palaIzquierda;
    private Pala palaDerecha;
    private int radio = 5;

    private boolean enPausa = false;
    private long pausaHasta = 0;

    public boolean isEnPausa() {
        return enPausa && System.currentTimeMillis() < pausaHasta;
    }

    public void iniciarPausa(long duracionMillis) {
        this.enPausa = true;
        this.pausaHasta = System.currentTimeMillis() + duracionMillis;
    }

    public void actualizar() {
        if (isEnPausa()) return;
        bola.mover();
    }

    public void reset() {
        this.bola.reset();
    }

    public GameState(){
        this.marcadorIzq = 0;
        this.marcadorDrch = 0;
        this.bola = new Bola(300, 200);
        this.palaIzquierda = new Pala(30, 170);
        this.palaDerecha = new Pala(560, 170);
    }


    public void actualizar(PongMove pongMove){
        if (pongMove.getDireccion().equals("UP") && (pongMove.getPaddle().equals("LEFT"))){
            moverIzquierdaArriba();
        }
        if (pongMove.getDireccion().equals("DOWN") && (pongMove.getPaddle().equals("LEFT"))){
            moverIzquierdaAbajo();
        }
        if (pongMove.getDireccion().equals("UP") && (pongMove.getPaddle().equals("RIGHT"))){
            moverDerechaArriba();
        }
        if (pongMove.getDireccion().equals("DOWN") && (pongMove.getPaddle().equals("RIGHT"))){
            moverDerechaAbajo();
        }
    }

    public boolean comprobarColisiones() {
        // Rebote en la derecha
        if ((bola.getX() + radio) > APP_VARIABLES.anchoVentanaPong) {
            bola.setX(APP_VARIABLES.anchoVentanaPong - radio);
            bola.setDx(-abs(bola.getDx()));
            bola.setVelocidad(bola.getVelocidad() + bola.getAceleracion());
            marcadorIzq++;
            return true;
        }

        // Rebote en la izquierda
        if ((bola.getX() - radio) < 0) {
            bola.setX(radio);
            bola.setDx(abs(bola.getDx()));
            bola.setVelocidad(bola.getVelocidad() + bola.getAceleracion());
            marcadorDrch++;
            return true;
        }

        // Rebote abajo
        if ((bola.getY() + radio) > APP_VARIABLES.altoVentanaPong) {
            bola.setY(APP_VARIABLES.altoVentanaPong - radio);
            bola.setDy(-abs(bola.getDy()));
            bola.setVelocidad(bola.getVelocidad() + bola.getAceleracion());
            return false;
        }

        // Rebote arriba
        if ((bola.getY() - radio) < 0) {
            bola.setY(radio);
            bola.setDy(abs(bola.getDy()));
            bola.setVelocidad(bola.getVelocidad() + bola.getAceleracion());
            return false;
        }

        // Rebote en la pala izquierda
        if ((bola.getX() - radio) < palaIzquierda.getX() + APP_VARIABLES.anchoPala &&
                (bola.getY() + radio) > palaIzquierda.getY() &&
                (bola.getY() - radio) < palaIzquierda.getY() + APP_VARIABLES.altoPala) {
            bola.setX(palaIzquierda.getX() + APP_VARIABLES.anchoPala + radio);
            bola.setDx(abs(bola.getDx()));
            bola.setVelocidad(bola.getVelocidad() + bola.getAceleracion());
            return false;
        }

        // Rebote en la pala derecha
        if ((bola.getX() + radio) > palaDerecha.getX() &&
                (bola.getY() + radio) > palaDerecha.getY() &&
                (bola.getY() - radio) < palaDerecha.getY() + APP_VARIABLES.altoPala) {
            bola.setX(palaDerecha.getX() - radio);
            bola.setDx(-abs(bola.getDx()));
            bola.setVelocidad(bola.getVelocidad() + bola.getAceleracion());
            return false;
        }
        return false;

    }


    private void moverIzquierdaArriba() {
        if (getPalaIzquierda().getY() > 0) {
            getPalaIzquierda().setY(getPalaIzquierda().getY() - 10);
        }
    }
    private void moverIzquierdaAbajo() {
        if (getPalaIzquierda().getY() < APP_VARIABLES.altoVentanaPong - altoPala) {
            getPalaIzquierda().setY(getPalaIzquierda().getY() + 10);
        }
    }
    private void moverDerechaArriba() {
        if (getPalaDerecha().getY() > 0) {
            getPalaDerecha().setY(getPalaDerecha().getY() - 10);
        }
    }
    private void moverDerechaAbajo() {
        if (getPalaDerecha().getY() < APP_VARIABLES.altoVentanaPong - altoPala) {
            getPalaDerecha().setY(getPalaDerecha().getY() + 10);
        }
    }

    public int getMarcadorIzq() {
        return marcadorIzq;
    }

    public void setMarcadorIzq(int marcadorIzq) {
        this.marcadorIzq = marcadorIzq;
    }

    public int getMarcadorDrch() {
        return marcadorDrch;
    }

    public void setMarcadorDrch(int marcadorDrch) {
        this.marcadorDrch = marcadorDrch;
    }

    public Bola getBola() {
        return bola;
    }

    public void setBola(Bola bola) {
        this.bola = bola;
    }

    public Pala getPalaIzquierda() {
        return palaIzquierda;
    }

    public void setPalaIzquierda(Pala palaIzquierda) {
        this.palaIzquierda = palaIzquierda;
    }

    public Pala getPalaDerecha() {
        return palaDerecha;
    }

    public void setPalaDerecha(Pala palaDerecha) {
        this.palaDerecha = palaDerecha;
    }

}
