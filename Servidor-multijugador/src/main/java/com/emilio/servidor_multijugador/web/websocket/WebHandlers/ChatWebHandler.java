package com.emilio.servidor_multijugador.web.websocket.WebHandlers;

import com.emilio.servidor_multijugador.Util.JsonUtils;
import com.emilio.servidor_multijugador.web.Mensajes.MensajeGeneral;
import com.emilio.servidor_multijugador.web.Mensajes.NumeroJugadoresMensaje;
import com.emilio.servidor_multijugador.web.websocket.data.ChatRoom;
import com.emilio.servidor_multijugador.web.websocket.data.Player;
import com.emilio.servidor_multijugador.web.websocket.data.Room;
import com.emilio.servidor_multijugador.web.websocket.handler.DataHandler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class ChatWebHandler extends TextWebSocketHandler {

    ChatRoom salaChat;
    private static int roomCounter = 1;

    public ChatWebHandler() {

        this.salaChat = new ChatRoom("Sala Chat");
    }
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);

        String nick = Objects.requireNonNull(session.getUri()).getQuery();
        Map<String, String> params = UriComponentsBuilder
                .fromUri(session.getUri())
                .build()
                .getQueryParams()
                .toSingleValueMap();

        String nickValue = params.get("nick");

        Player player = new Player();
        player.setSession(session);
        player.setNick(nickValue);

        salaChat.addPlayer(player);


        NumeroJugadoresMensaje numeroJugadoresMensaje = new NumeroJugadoresMensaje(salaChat.getPlayers().size());
        MensajeGeneral mensajeGeneral = new MensajeGeneral("NUMERO_JUGADORES_ACTIVOS", numeroJugadoresMensaje);

        broadcastMessage(JsonUtils.toJson(mensajeGeneral));
    }
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String mensaje = message.getPayload();

        mandarMensaje(mensaje,session);
    }

    public void mandarMensaje(String mensaje, WebSocketSession session) {
        salaChat.mandarMensaje(mensaje, session);
    }

    public void broadcastMessage(String mensaje) {
        salaChat.broadcastMessage(mensaje);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("Error en la conexión: " + exception.getMessage());
        super.handleTransportError(session, exception);
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        salaChat.removePlayer(session);

        // Actualizar el número de jugadores activos
        NumeroJugadoresMensaje numeroJugadoresMensaje = new NumeroJugadoresMensaje(salaChat.getPlayers().size());
        MensajeGeneral mensajeGeneral = new MensajeGeneral("NUMERO_JUGADORES_ACTIVOS", numeroJugadoresMensaje);
        broadcastMessage(JsonUtils.toJson(mensajeGeneral));

        super.afterConnectionClosed(session, status);
    }

}
