package Cliente.Handlers;

import Cliente.Controladores.PrincipalController;
import Cliente.Mensajes.*;
import Juegos.Pong.Modelos.GameState;
import Util.JsonUtils;
import com.sun.source.tree.IfTree;
import javafx.application.Platform;

import java.util.List;

public class ChatHandler implements MessageHandler{

    private PrincipalController principalController;

    @Override
    public void handle(Object message) {

        try {
            String mensajeStr = (String) message;
            MensajeGeneral data = JsonUtils.fromJson(mensajeStr, MensajeGeneral.class);

            if (data.getType().equals("CHAT")) {
                // Intentar convertir a GameStateMensaje
                String dataString = JsonUtils.toJson(data.getData());
                ChatData chatData = JsonUtils.fromJson(dataString, ChatData.class);
                Platform.runLater(() -> {
                    principalController.actualizarChat(chatData);
                });
            }

            if (data.getType().equals("NUMERO_JUGADORES_ACTIVOS")) {
                // Intentar convertir a GameStateMensaje
                String dataString = JsonUtils.toJson(data.getData());
                NumeroJugadoresMensaje numeroJugadoresMensaje = JsonUtils.fromJson(dataString, NumeroJugadoresMensaje.class);
                Platform.runLater(() -> {
                    principalController.actualizarNumeroJugadores(numeroJugadoresMensaje.getNumeroJugadores());
                });
            }
        } catch (Exception e) {
            System.err.println("Error al manejar el mensaje: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setPrincipalControler(PrincipalController principalController) {
        this.principalController = principalController;
    }

    @Override
    public void handleError(String error) {
        // Handle error
        System.err.println("Error: " + error);
    }

    @Override
    public void handleDisconnect() {
        // Handle disconnection-
        System.out.println("Client disconnected");
    }
}
