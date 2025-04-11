package App;

import javax.websocket.*;
import java.net.URI;

public class WebSocketConnection {

    public void establecerConexionChat(String uri) {
        try {
            // Crear el contenedor WebSocket
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            // Conectar al servidor WebSocket, pasando la clase ChatWebSocket como manejador
            container.connectToServer(ChatWeb.class, new URI(uri));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}