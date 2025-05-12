package com.emilio.servidor_multijugador.game.pong.modelos;

import com.emilio.servidor_multijugador.Util.CONFIG;
import com.emilio.servidor_multijugador.web.Mensajes.PongMove;

import static java.lang.Math.abs;

public class GameState {

    private int marcadorIzq;
    private int marcadorDrch;
    private Bola bola;
    private Pala palaIzquierda;
    private Pala palaDerecha;
    private int radio = 5;
    private final double  anchoVentanaPong = 500;
    private final double altoVentanaPong = 600;



    public GameState(double xPalaDrch, double xPalaIzq, double xBola, double yBola) {
        this.marcadorIzq = 0;
        this.marcadorDrch = 0;
        this.bola = new Bola(xBola, yBola);
        this.palaIzquierda = new Pala(xPalaIzq, 170);
        this.palaDerecha = new Pala(xPalaDrch, 170);
    }

    public void actualizar() {
        bola.mover();
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

    public void comprobarColisiones() {
        // Rebote en la derecha
        if ((bola.getX() + radio) > anchoVentanaPong) {
            bola.setX(anchoVentanaPong - radio);
            bola.setDx(-abs(bola.getDx()));
            bola.setVelocidad(bola.getVelocidad() + bola.getAceleracion());
            marcadorIzq++;
        }

        // Rebote en la izquierda
        if ((bola.getX() - radio) < 0) {
            bola.setX(radio);
            bola.setDx(abs(bola.getDx()));
            bola.setVelocidad(bola.getVelocidad() + bola.getAceleracion());
            marcadorDrch++;
        }

        // Rebote abajo
        if ((bola.getY() + radio) > altoVentanaPong) {
            bola.setY(altoVentanaPong - radio);
            bola.setDy(-abs(bola.getDy()));
            bola.setVelocidad(bola.getVelocidad() + bola.getAceleracion());
        }

        // Rebote arriba
        if ((bola.getY() - radio) < 0) {
            bola.setY(radio);
            bola.setDy(abs(bola.getDy()));
            bola.setVelocidad(bola.getVelocidad() + bola.getAceleracion());
        }

        /*
        // Rebote en la pala izquierda
        if ((bola.getX() - radio) < palaIzquierda.getX() + anchoPala &&
                (bola.getY() + radio) > palaIzquierda.getY() &&
                (bola.getY() - radio) < palaIzquierda.getY() + altoPala) {
            bola.setX(palaIzquierda.getX() + anchoPala + radio);
            bola.setDx(abs(bola.getDx()));
            bola.setVelocidad(bola.getVelocidad() + bola.getAceleracion());
        }

        // Rebote en la pala derecha
        if ((bola.getX() + radio) > palaDerecha.getX() &&
                (bola.getY() + radio) > palaDerecha.getY() &&
                (bola.getY() - radio) < palaDerecha.getY() + altoPala) {
            bola.setX(palaDerecha.getX() - radio);
            bola.setDx(-abs(bola.getDx()));
            bola.setVelocidad(bola.getVelocidad() + bola.getAceleracion());
        }
         */
    }


    private void moverIzquierdaArriba() {
        if (getPalaIzquierda().getY() > 0) {
            getPalaIzquierda().setY(getPalaIzquierda().getY() - 10);
        }
    }
    private void moverIzquierdaAbajo() {
        if (getPalaIzquierda().getY() < CONFIG.altoVentanaPong - CONFIG.altoPala) {
            getPalaIzquierda().setY(getPalaIzquierda().getY() + 10);
        }
    }
    private void moverDerechaArriba() {
        if (getPalaDerecha().getY() > 0) {
            getPalaDerecha().setY(getPalaDerecha().getY() - 10);
        }
    }
    private void moverDerechaAbajo() {
        if (getPalaDerecha().getY() < CONFIG.altoVentanaPong - CONFIG.altoPala) {
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
