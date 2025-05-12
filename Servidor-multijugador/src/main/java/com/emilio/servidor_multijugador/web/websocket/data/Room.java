package com.emilio.servidor_multijugador.web.websocket;

import com.emilio.servidor_multijugador.web.websocket.data.Player;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Room {
    private String id;
    private List<Player> players;

    public Room(String id) {
        this.id = id;
        this.players = new ArrayList<>();
    }
    public String getId() {
        return id;
    }

    public void addPlayer(Player player) {
        if (players.size() < 2) {
            players.add(player);
        }
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void mandarMensaje(String mensaje, WebSocketSession emisor) {
        for (Player player : players) {
            if (!player.getSession().equals(emisor)) {
                try {
                    player.getSession().sendMessage(new TextMessage(mensaje));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
