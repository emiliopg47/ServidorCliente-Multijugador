package com.emilio.servidor_multijugador.persistencia.modelos;

public class TopRanking {

    private int posicionRanking;
    private String nick;
    private int puntos;
    private byte[] imagen;

    public TopRanking(int posicionRanking, String nick, int puntos, byte[] imagen) {
        this.posicionRanking = posicionRanking;
        this.nick = nick;
        this.puntos = puntos;
        this.imagen = imagen;
    }

    public TopRanking() {
        // Constructor por defecto necesario para la deserialización
    }

    public int getPosicionRanking() {
        return posicionRanking;
    }

    public void setPosicionRanking(int posicionRanking) {
        this.posicionRanking = posicionRanking;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}

