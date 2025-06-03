package Juegos.Pong.Modelos;

import Cliente.Mensajes.GameStateMensaje;
import Config.APP_VARIABLES;

import static java.lang.Math.abs;

public class GameState {

    private int marcadorIzq;
    private int marcadorDrch;
    private Bola bola;
    private Pala palaIzquierda;
    private Pala palaDerecha;
    private int radio = 5;


    public GameState(double xPalaDrch, double xPalaIzq, double xBola, double yBola) {
        this.marcadorIzq = 0;
        this.marcadorDrch = 0;
        this.bola = new Bola(xBola, yBola);
        this.palaIzquierda = new Pala(xPalaIzq, 170);
        this.palaDerecha = new Pala(xPalaDrch, 170);
    }

    public GameState(GameStateMensaje gameStateMensaje){
        this.marcadorIzq = gameStateMensaje.getMarcadorIzquierda();
        this.marcadorDrch = gameStateMensaje.getMarcadorDerecha();
        this.bola = new Bola(gameStateMensaje.getxBola(), gameStateMensaje.getyBola());
        this.palaIzquierda = new Pala(APP_VARIABLES.xPalaIzquierda, gameStateMensaje.getPalaIzquierda());
        this.palaDerecha = new Pala(APP_VARIABLES.xPalaDerecha, gameStateMensaje.getPalaDerecha());
    }

    public void actualizar() {
        bola.mover();
    }

    public void comprobarColisiones() {
        // Rebote en la derecha
        if ((bola.getX() + radio) > APP_VARIABLES.anchoVentanaPong) {
            bola.setX(APP_VARIABLES.anchoVentanaPong - radio);
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
        if ((bola.getY() + radio) > APP_VARIABLES.altoVentanaPong) {
            bola.setY(APP_VARIABLES.altoVentanaPong - radio);
            bola.setDy(-abs(bola.getDy()));
            bola.setVelocidad(bola.getVelocidad() + bola.getAceleracion());
        }

        // Rebote arriba
        if ((bola.getY() - radio) < 0) {
            bola.setY(radio);
            bola.setDy(abs(bola.getDy()));
            bola.setVelocidad(bola.getVelocidad() + bola.getAceleracion());
        }

        // Rebote en la pala izquierda
        if ((bola.getX() - radio) < palaIzquierda.getX() + APP_VARIABLES.anchoPala &&
                (bola.getY() + radio) > palaIzquierda.getY() &&
                (bola.getY() - radio) < palaIzquierda.getY() + APP_VARIABLES.altoPala) {
            bola.setX(palaIzquierda.getX() + APP_VARIABLES.anchoPala + radio);
            bola.setDx(abs(bola.getDx()));
            bola.setVelocidad(bola.getVelocidad() + bola.getAceleracion());
        }

        // Rebote en la pala derecha
        if ((bola.getX() + radio) > palaDerecha.getX() &&
                (bola.getY() + radio) > palaDerecha.getY() &&
                (bola.getY() - radio) < palaDerecha.getY() + APP_VARIABLES.altoPala) {
            bola.setX(palaDerecha.getX() - radio);
            bola.setDx(-abs(bola.getDx()));
            bola.setVelocidad(bola.getVelocidad() + bola.getAceleracion());
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
