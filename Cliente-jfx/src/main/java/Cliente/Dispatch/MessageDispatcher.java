package Cliente.Dispatch;

import Cliente.Handlers.ChatHandler;
import Cliente.Mensajes.MensajeGeneral;
import Util.JsonUtils;

public class MessageDispatcher {

    //private final Map<String, MessageHandler> handlers = new HashMap<>();


    public void dispatch(String messageJson) {
        MensajeGeneral message = JsonUtils.fromJson(messageJson, MensajeGeneral.class);

        switch (message.getType()) {
            case "chat":
                new ChatHandler().handle(message.getData());
                break;
            default:
                System.out.println("Tipo de mensaje no reconocido: " + message.getType());
                break;
        }

    }
}

