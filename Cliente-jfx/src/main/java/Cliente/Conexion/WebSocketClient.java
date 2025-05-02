package Cliente.Conexion;

import Cliente.Dispatch.MessageDispatcher;
import jakarta.websocket.*;

import java.net.URI;

public abstract class WebSocketClient extends Endpoint {

    protected Session session;
    protected MessageDispatcher dispatcher;

    @Override
    public void onOpen(Session session, EndpointConfig config) {
        this.session = session;
        session.addMessageHandler(String.class, this::handleMessage);
        System.out.println("Conexión establecida con el servidor.");
    }

    public void sendMessage(String message) {
        if (session != null && session.isOpen()) {
            session.getAsyncRemote().sendText(message);
        } else {
            System.out.println("La conexión no está abierta.");
        }
    }

    public void conectar(String uri) throws Exception {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        ClientEndpointConfig config = ClientEndpointConfig.Builder.create().build();
        container.connectToServer(this, config, new URI(uri));
    }

    protected void handleMessage(String mensaje) {
        if (dispatcher != null) {
            dispatcher.dispatch(mensaje);
        } else {
            System.err.println("Dispatcher no inicializado, mensaje recibido: " + mensaje);
        }
    }

    public void close() {
        if (session != null && session.isOpen()) {
            try {
                session.close();
                System.out.println("Conexión cerrada.");
            } catch (Exception e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    public void setDispatcher(MessageDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }
}
