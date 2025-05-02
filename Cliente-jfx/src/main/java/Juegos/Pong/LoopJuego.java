package Juegos.Pong;

import Juegos.Pong.Controladores.PongController;
import Juegos.Pong.Modelos.Game;
import Util.CONFIG;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;

public class LoopJuego extends AnimationTimer {

    private final Game estado;
    private final PongController controlador;

    public LoopJuego(Game estado, PongController controlador) {
        this.estado = estado;
        this.controlador = controlador;
    }

    @Override
    public void handle(long now) {
        // 1. Actualizar lógica del juego
        estado.actualizar();

        // 2. Comprobar las teclas activas
        comprobarTeclas();

        // 3. Comprobar colisiones
        estado.comprobarColisiones();

        // 4. Actualizar controladores o UI
        controlador.actualizar(estado);
    }

    public void comprobarTeclas() {
        // Comprobar teclas activas y mover las palas
        if (controlador.getTeclasActivas().contains(KeyCode.W)) {
            moverIzquierdaArriba();
        }
        if (controlador.getTeclasActivas().contains(KeyCode.S)) {
            moverIzquierdaAbajo();
        }
        if (controlador.getTeclasActivas().contains(KeyCode.UP)) {
            moverDerechaArriba();
        }
        if (controlador.getTeclasActivas().contains(KeyCode.DOWN)) {
            moverDerechaAbajo();
        }
    }

    private void moverIzquierdaArriba() {
        if (estado.getPalaIzquierda().getY() > 0) {
            estado.getPalaIzquierda().setY(estado.getPalaIzquierda().getY() - 10);
        }
    }
    private void moverIzquierdaAbajo() {
        if (estado.getPalaIzquierda().getY() < CONFIG.altoVentanaPong - CONFIG.altoPala) {
            estado.getPalaIzquierda().setY(estado.getPalaIzquierda().getY() + 10);
        }
    }
    private void moverDerechaArriba() {
        if (estado.getPalaDerecha().getY() > 0) {
            estado.getPalaDerecha().setY(estado.getPalaDerecha().getY() - 10);
        }
    }
    private void moverDerechaAbajo() {
        if (estado.getPalaDerecha().getY() < CONFIG.altoVentanaPong - CONFIG.altoPala) {
            estado.getPalaDerecha().setY(estado.getPalaDerecha().getY() + 10);
        }
    }
}
