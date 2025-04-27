package Conexion;

import Dispatch.MessageDispatcher;

import javax.websocket.*;
import java.net.URI;

@ClientEndpoint
public abstract class WebSocketClient {

    protected Session session;
    protected MessageDispatcher dispatcher;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        System.out.println("Conexión establecida con el servidor.");
    }

    @OnMessage
    public void onMessage(String message) {
        handleMessage(message);
    }

    public void sendMessage(String message) {
        if (session != null && session.isOpen()) {
            session.getAsyncRemote().sendText(message);
        } else {
            System.out.println("La conexión no está abierta.");
        }
    }

    public void conectar(String uri) throws Exception {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, new URI(uri));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected void handleMessage(String mensaje) {
        if (dispatcher != null) {
            dispatcher.dispatch(mensaje);
        } else {
            System.err.println("Dispatcher no inicializado, mensaje recibido: " + mensaje);
        }
    }


    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("Conexión cerrada: " + closeReason.getReasonPhrase());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.err.println("Error en la conexión: " + throwable.getMessage());
    }

    public void setDispatcher(MessageDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }
}