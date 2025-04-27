package Handlers;

public interface MessageHandler {
    void handle(Object data);
    void handleError(String error);
    void handleDisconnect();
}
