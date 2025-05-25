package com.emilio.servidor_multijugador.persistencia.modelos;

import java.time.Instant;
import java.time.LocalDate;

public class HistorialGameDTO {
    private String idJugador1Nick;
    private String idJugador2Nick;
    private Long duracionSeg;
    private LocalDate fechaHora;
    private int puntosJ1;
    private int puntosJ2;
    private Byte winner;

    public HistorialGameDTO(String nickJugador1, String nickJugador2, Long duracionSeg, LocalDate fechaHora, Integer puntosJ1, Integer puntosJ2, Byte winner) {
        this.idJugador1Nick = nickJugador1;
        this.idJugador2Nick = nickJugador2;
        this.duracionSeg = duracionSeg;
        this.fechaHora = fechaHora;
        this.puntosJ1 = puntosJ1;
        this.puntosJ2 = puntosJ2;
        this.winner = winner;
    }

    public HistorialGameDTO() {
        // Constructor por defecto necesario para la deserialización
    }

    public String getIdJugador1Nick() {
        return idJugador1Nick;
    }

    public void setIdJugador1Nick(String idJugador1Nick) {
        this.idJugador1Nick = idJugador1Nick;
    }

    public String getIdJugador2Nick() {
        return idJugador2Nick;
    }

    public void setIdJugador2Nick(String idJugador2Nick) {
        this.idJugador2Nick = idJugador2Nick;
    }

    public Long getDuracionSeg() {
        return duracionSeg;
    }

    public void setDuracionSeg(Long duracionSeg) {
        this.duracionSeg = duracionSeg;
    }

    public LocalDate getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDate fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Integer getPuntosJ1() {
        return puntosJ1;
    }

    public void setPuntosJ1(Integer puntosJ1) {
        this.puntosJ1 = puntosJ1;
    }

    public Integer getPuntosJ2() {
        return puntosJ2;
    }

    public void setPuntosJ2(Integer puntosJ2) {
        this.puntosJ2 = puntosJ2;
    }

    public Byte getWinner() {
        return winner;
    }

    public void setWinner(Byte winner) {
        this.winner = winner;
    }
}
