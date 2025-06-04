package com.emilio.servidor_multijugador.web.websocket.data;

import org.springframework.web.socket.WebSocketSession;

public class ChatRoom extends Room {


    public ChatRoom(String id) {
        super(id);
    }

    @Override
    public void addPlayer(Player player) {
        super.players.add(player);
    }

    @Override
    public void mandarMensaje(String mensaje, WebSocketSession emisor) {
        super.mandarMensaje(mensaje, emisor);
        guardarMensajeBD(mensaje);
    }

    public void broadcastMessage(String mensaje) {
        for (Player player : super.players) {
            try {
                player.getSession().sendMessage(new org.springframework.web.socket.TextMessage(mensaje));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void guardarMensajeBD(String mensaje) {
        // Comming soon....
    }
}
