package Cliente.Conexion;

import Cliente.Controladores.PrincipalController;
import Cliente.Dispatch.MessageDispatcher;
import Cliente.Handlers.ChatHandler;
import Cliente.Mensajes.ChatData;
import Cliente.Mensajes.MensajeGeneral;
import Config.UsuarioLogeado;
import Util.JsonUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatClient extends WebSocketClient {

    private PrincipalController principalController;

    public ChatClient(PrincipalController principalController) {
        this.principalController = principalController; // Pasas el controlador del chat
        this.dispatcher = new MessageDispatcher();
    }

    public ChatClient(){

    }

    @Override
    protected void handleMessage(String mensaje) {
        //super.handleMessage(mensaje);
        ChatHandler chatHandler = new ChatHandler();
        chatHandler.setPrincipalControler(principalController);
        chatHandler.handle(mensaje);
    }

    public void enviarMensajeChat(String contenido) {
        LocalDateTime fechahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaHoraFormateada = fechahora.format(formato);
        // Prepara un JSON simple de tipo "chat"
        ChatData chatData = new ChatData(contenido, UsuarioLogeado.nick, fechaHoraFormateada);
        MensajeGeneral message = new MensajeGeneral("CHAT", chatData);

        principalController.actualizarChat(chatData);

        String json = JsonUtils.toJson(message);
        sendMessage(json);
    }
}
