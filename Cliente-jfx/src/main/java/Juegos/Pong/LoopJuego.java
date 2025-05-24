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

    private String paddleUpIzquierda;
    private String paddleDownIzquierda;
    private String paddleUpDerecha;
    private String paddleDownDerecha;

    private int idPlayer;

    public LoopJuego(PongClient pongClient, PongController controlador, int idPlayer) {
        this.pongClient = pongClient;
        this.controlador = controlador;
        if (idPlayer == 1){
            // Jugador uno controla la pala izquierda
            this.paddleUpIzquierda = JsonUtils.toJson(new MensajeGeneral(("MOVE_PADDLE"), new PongMove("UP", "LEFT")));
            this.paddleDownIzquierda = JsonUtils.toJson(new MensajeGeneral(("MOVE_PADDLE"), new PongMove("DOWN", "LEFT")));
            this.paddleUpDerecha = JsonUtils.toJson(new MensajeGeneral(("MOVE_PADDLE"), new PongMove("UP", "LEFT")));
            this.paddleDownDerecha = JsonUtils.toJson(new MensajeGeneral(("MOVE_PADDLE"), new PongMove("DOWN", "LEFT")));
        }
        if (idPlayer == 2){
            // Jugador dos controla la pala derecha
            this.paddleUpIzquierda = JsonUtils.toJson(new MensajeGeneral(("MOVE_PADDLE"), new PongMove("UP", "RIGHT")));
            this.paddleDownIzquierda = JsonUtils.toJson(new MensajeGeneral(("MOVE_PADDLE"), new PongMove("DOWN", "RIGHT")));
            this.paddleUpDerecha = JsonUtils.toJson(new MensajeGeneral(("MOVE_PADDLE"), new PongMove("UP", "RIGHT")));
            this.paddleDownDerecha = JsonUtils.toJson(new MensajeGeneral(("MOVE_PADDLE"), new PongMove("DOWN", "RIGHT")));
        }

    }

    @Override
    public void handle(long now) {
        // El loop del cliente solo comprobara las teclas
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