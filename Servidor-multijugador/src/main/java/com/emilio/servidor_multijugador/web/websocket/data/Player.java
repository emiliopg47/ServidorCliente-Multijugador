package com.emilio.servidor_multijugador.web.websocket.data;

import org.springframework.web.socket.WebSocketSession;

public class Player {
    WebSocketSession session;
    String nick;
    String elo;

    public WebSocketSession getSession() {
        return session;
    }

    public void setSession(WebSocketSession session) {
        this.session = session;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getElo() {
        return elo;
    }

    public void setElo(String elo) {
        this.elo = elo;
    }
}
