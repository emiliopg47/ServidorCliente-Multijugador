package Cliente.Conexion;

import Cliente.Dispatch.MessageDispatcher;
import Cliente.Handlers.PongHandler;
import Juegos.Pong.Controladores.PongController;

public class PongClient extends WebSocketClient{
    private String nick;
    private PongController pongController;

    public PongClient(String nick, PongController pongController) {
        this.nick = nick;
        this.pongController = pongController; // Pasas el controlador del chat
        this.dispatcher = new MessageDispatcher();
    }

    @Override
    protected void handleMessage(String mensaje) {
        //super.handleMessage(mensaje);
        PongHandler pongHandler = new PongHandler();
        pongHandler.setPongController(pongController);
        pongHandler.handle(mensaje);
    }

    public void movePaddle(String contenido) {

    }

    public void setPongController(PongController pongController) {
        this.pongController = pongController;
    }
}
