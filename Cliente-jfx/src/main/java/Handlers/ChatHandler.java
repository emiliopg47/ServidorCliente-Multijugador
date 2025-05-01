package Handlers;

import Controladores.ChatController;
import Modelos.Mensajes.ChatData;
import Util.JsonUtils;
import javafx.application.Platform;

public class ChatHandler implements MessageHandler{

    private ChatController chatController;

    @Override
    public void handle(Object message) {
        String mensajeStr = (String) message;
        ChatData data = JsonUtils.fromJson(mensajeStr, ChatData.class);
        Platform.runLater(() -> {
            // Aquí pasas el mensaje recibido al controlador para que lo muestre
            chatController.actualizarChat(data);
        });

    }

    public void setChatController(ChatController chatController) {
        this.chatController = chatController;
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
