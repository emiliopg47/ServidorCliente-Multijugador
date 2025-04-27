package Dispatch;

import Handlers.ChatHandler;
import Modelos.Mensajes.MessageDTO;
import Util.JsonUtils;

import java.util.HashMap;
import java.util.Map;

public class MessageDispatcher {

    //private final Map<String, MessageHandler> handlers = new HashMap<>();


    public void dispatch(String messageJson) {
        MessageDTO message = JsonUtils.fromJson(messageJson, MessageDTO.class);

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

