package com.emilio.servidor_multijugador.web.websocket.data;

import org.springframework.web.socket.WebSocketSession;

public class ChatRoom extends Room {


    public ChatRoom(String id) {
        super(id);
    }

    @Override
    public void addPlayer(Player player) {
        super.players.add(player);
        String mensajeBienvenida = "¡Bienvenido al chat, " + player.getNick() + "!";
        mandarMensaje(mensajeBienvenida, player.getSession());
    }

    @Override
    public void mandarMensaje(String mensaje, WebSocketSession emisor) {
        super.mandarMensaje(mensaje, emisor);
        guardarMensajeBD(mensaje);
    }

    public void guardarMensajeBD(String mensaje) {
        // Comming soon....
    }
}
