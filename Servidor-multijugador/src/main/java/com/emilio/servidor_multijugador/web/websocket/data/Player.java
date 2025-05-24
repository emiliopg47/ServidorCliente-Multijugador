package com.emilio.servidor_multijugador.web.websocket.data;

import org.springframework.web.socket.WebSocketSession;

public class Player {
    WebSocketSession session;
    private int id;
    private String nick;
    private int elo;
    private byte[] fotoPerfil;

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

    public int getElo() {
        return elo;
    }

    public void setElo(int elo) {
        this.elo = elo;
    }

    public byte[] getFotoPerfil() {
        return fotoPerfil;
    }
    public void setFotoPerfil(byte[] fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
