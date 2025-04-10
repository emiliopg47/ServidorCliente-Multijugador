package com.emilio.servidor_multijugador.web.websocket;

import com.emilio.servidor_multijugador.web.websocket.data.Player;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ChatWebSocketHandler  extends TextWebSocketHandler {

    Map<String, Room> rooms;
    private static int roomCounter = 1;

    public ChatWebSocketHandler() {
        this.rooms = new HashMap<>();
    }
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //Si es null, no existen salas con un jugador toca crear una nueva.
        //Si no es null, existe una sala con un jugador, se une a la sala
        super.afterConnectionEstablished(session);
        Player player = new Player();
        player.setSession(session);
        Room room = buscarSalaConJugador();
        if (room == null) {
            room = nuevaSala();
            room.addPlayer(player);
        } else {
            room.addPlayer(player);
        }
        if(room.getPlayers().size() == 2){
            startGame(room);
        }
    }
    @Override
    protected void handleTextMessage(WebSocketSession player, TextMessage message) throws IOException {
        Room room = buscarMiSala(player);
        if (room != null) {
            room.mandarMensaje(message.getPayload(), player);
        }
    }
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("Error en la conexión: " + exception.getMessage());
        super.handleTransportError(session, exception);
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("Cliente desconectado: " + session.getId());
        super.afterConnectionClosed(session, status);
    }
    private Room nuevaSala(){
        System.out.println("No existe sala, creando una nueva");
        Room room = new Room("Sala " + roomCounter++);
        rooms.put(room.getId(), room);
        return room;
    }
    private void startGame(Room room) {
        for (Player player : room.getPlayers()) {
            try {
                player.getSession().sendMessage(new TextMessage("El chat ha comenzado en la sala: " + room.getId()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private Room buscarMiSala(WebSocketSession session){
        for (Room room : rooms.values()) {
            for (Player player: room.getPlayers()) {
                if (player.getSession().equals(session)) {
                    return room;
                }
            }
        }
        return null;
    }
    private Room buscarSalaConJugador(){
        for (Room room : rooms.values()) {
            if (room.getPlayers().size() < 2) {
                return room;
            }
        }
        return null;
    }
}
