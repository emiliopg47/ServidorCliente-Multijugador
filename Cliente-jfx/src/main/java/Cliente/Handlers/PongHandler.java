package Cliente.Handlers;

import Cliente.Mensajes.GameEndMensaje;
import Cliente.Mensajes.GameStateMensaje;
import Cliente.Mensajes.MensajeGeneral;
import Cliente.Mensajes.PlayerMensaje;
import Juegos.Pong.Controladores.PongController;
import Juegos.Pong.Modelos.GameState;
import Util.JsonUtils;
import javafx.application.Platform;

import java.util.List;

public class PongHandler implements MessageHandler{

    private PongController pongController;

    @Override
    public void handle(Object message) {
        try {
            String mensajeStr = (String) message;
            MensajeGeneral data = JsonUtils.fromJson(mensajeStr, MensajeGeneral.class);



            if (data.getType().equals("ESTADO")) {
                // Intentar convertir a GameStateMensaje
                String dataString = JsonUtils.toJson(data.getData());
                GameStateMensaje gameStateMensaje = JsonUtils.fromJson(dataString, GameStateMensaje.class);

                // Crear GameState y actualizar el controlador
                GameState game = new GameState(gameStateMensaje);
                Platform.runLater(() -> {
                    pongController.actualizar(game);
                });
            }

            if (data.getType().equals("INFO_GAME")) {
                // Intentar convertir a PlayerStateMensaje
                String dataString = JsonUtils.toJson(data.getData());
                List<PlayerMensaje> playerMensajes = JsonUtils.fromJsonListPlayer(dataString, PlayerMensaje.class);

                // Actualizar el controlador con el estado del jugador

                Platform.runLater(() -> {
                    pongController.actualizarPlayer(playerMensajes);
                    if (!pongController.isStarted()) {
                        pongController.startGame();
                    }
                });


            }

            if (data.getType().equals("FIN")) {
                String dataString = JsonUtils.toJson(data.getData());
                GameEndMensaje gameEndMensaje = JsonUtils.fromJson(dataString, GameEndMensaje.class);

                // Fin del juego
                Platform.runLater(() -> {
                    pongController.mostrarFinJuego(gameEndMensaje);
                });
            }


        } catch (Exception e) {
            System.err.println("Error al manejar el mensaje: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setPongController(PongController pongController) {
        this.pongController = pongController;
    }

    @Override
    public void handleError(String error) {
        // Handle error
        System.err.println("Error: " + error);
    }

    @Override
    public void handleDisconnect() {
        // Handle disconnection
        System.out.println("Client disconnected");
    }
}
