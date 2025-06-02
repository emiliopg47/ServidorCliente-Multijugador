package Cliente.Handlers;

import Cliente.Controladores.PrincipalController;
import Cliente.Mensajes.ChatData;
import Util.JsonUtils;
import javafx.application.Platform;

public class ChatHandler implements MessageHandler{

    private PrincipalController principalController;

    @Override
    public void handle(Object message) {
        String mensajeStr = (String) message;
        ChatData data = JsonUtils.fromJson(mensajeStr, ChatData.class);
        Platform.runLater(() -> {
            principalController.actualizarChat(data);
        });

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
