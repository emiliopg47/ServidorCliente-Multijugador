package com.emilio.servidor_multijugador.web.apirest.response;

import com.emilio.servidor_multijugador.persistencia.modelos.Juego;

public class GameInfoResponse {

    private String nombre;
    private String descripcion;
    private String comoJugar;

    public GameInfoResponse(String nombre, String descripcion, String como_jugar) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.comoJugar = como_jugar;
    }

    public GameInfoResponse(Juego juego){
        this.nombre = juego.getNombre();
        this.descripcion = juego.getDescripcion();
        this.comoJugar = juego.getComo_jugar();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getComoJugar() {
        return comoJugar;
    }

    public void setComoJugar(String comoJugar) {
        this.comoJugar = comoJugar;
    }
}
