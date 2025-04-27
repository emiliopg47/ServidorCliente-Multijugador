package Conexion;

import Controladores.ChatController;
import Dispatch.MessageDispatcher;
import Handlers.ChatHandler;
import Modelos.Mensajes.ChatData;
import Modelos.Mensajes.MessageDTO;
import Util.JsonUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatClient extends WebSocketClient {

    private String nick;
    private ChatController chatController;

    public ChatClient(String nick, ChatController chatController) {
        this.nick = nick;
        this.chatController = chatController; // Pasas el controlador del chat
        this.dispatcher = new MessageDispatcher();
    }

    @Override
    protected void handleMessage(String mensaje) {
        //super.handleMessage(mensaje);
        ChatHandler chatHandler = new ChatHandler();
        chatHandler.handle(mensaje);
    }

    public void enviarMensajeChat(String contenido) {
        LocalDateTime fechahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaHoraFormateada = fechahora.format(formato);
        // Prepara un JSON simple de tipo "chat"
        ChatData chatData = new ChatData(contenido, nick, fechaHoraFormateada);
        MessageDTO message = new MessageDTO("chat", chatData);
        String json = JsonUtils.toJson(message);
        sendMessage(json);
    }
}
