package Juegos.Pong;

import Cliente.Conexion.PongClient;
import Cliente.Mensajes.MensajeGeneral;
import Cliente.Mensajes.PongMove;
import Juegos.Pong.Controladores.PongController;
import Util.JsonUtils;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;

public class LoopJuego extends AnimationTimer {

    private final PongClient pongClient;
    private final PongController controlador;

    private final String paddleUpIzquierda;
    private String paddleDownIzquierda;
    private String paddleUpDerecha;
    private String paddleDownDerecha;

    public LoopJuego(PongClient pongClient, PongController controlador) {
        this.pongClient = pongClient;
        this.controlador = controlador;
        this.paddleUpIzquierda = JsonUtils.toJson(new MensajeGeneral(("MOVE_PADDLE"), new PongMove("UP", "LEFT")));
        this.paddleDownIzquierda = JsonUtils.toJson(new MensajeGeneral(("MOVE_PADDLE"), new PongMove("DOWN", "LEFT")));
        this.paddleUpDerecha = JsonUtils.toJson(new MensajeGeneral(("MOVE_PADDLE"), new PongMove("UP", "RIGHT")));
        this.paddleDownDerecha = JsonUtils.toJson(new MensajeGeneral(("MOVE_PADDLE"), new PongMove("DOWN", "RIGHT")));
    }

    @Override
    public void handle(long now) {
        comprobarTeclas();
    }

    public void comprobarTeclas() {
        if (controlador.getTeclasActivas().contains(KeyCode.W)) {
            pongClient.movePaddle(paddleUpIzquierda);
        }
        if (controlador.getTeclasActivas().contains(KeyCode.S)) {
            pongClient.movePaddle(paddleDownIzquierda);
        }
        if (controlador.getTeclasActivas().contains(KeyCode.UP)) {
            pongClient.movePaddle(paddleUpDerecha);
        }
        if (controlador.getTeclasActivas().contains(KeyCode.DOWN)) {
            pongClient.movePaddle(paddleDownDerecha);
        }
    }
}


/*
public class LoopJuego extends AnimationTimer {

    private final GameState estado;
    private final PongController controlador;

    public LoopJuego(GameState estado, PongController controlador) {
        this.estado = estado;
        this.controlador = controlador;
    }

    @Override
    public void handle(long now) {
        comprobarTeclas();
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


 */