package App;

import javax.websocket.*;
import java.net.URI;

@ClientEndpoint
public abstract class WebSocketClient {

    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        System.out.println("Conexión establecida con el servidor.");
    }

    @OnMessage
    public void onMessage(String message) {
        handleMessage(message);
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("Conexión cerrada: " + closeReason.getReasonPhrase());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.err.println("Error en la conexión: " + throwable.getMessage());
    }

    public void sendMessage(String message) {
        if (session != null && session.isOpen()) {
            session.getAsyncRemote().sendText(message);
        } else {
            System.out.println("La conexión no está abierta.");
        }
    }

    protected abstract void handleMessage(String message);

    public void establecerConexion(String uri) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, new URI(uri));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}