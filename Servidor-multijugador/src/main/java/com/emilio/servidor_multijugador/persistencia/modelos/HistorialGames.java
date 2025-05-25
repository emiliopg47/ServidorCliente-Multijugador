package com.emilio.servidor_multijugador.persistencia.modelos;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "historial_games")
public class HistorialGames {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_game", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_jugador1", nullable = false)
    private Usuario idJugador1;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_jugador2", nullable = false)
    private Usuario idJugador2;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_juego", nullable = false)
    private Juego idJuego;

    @Column(name = "puntos_j1", nullable = false)
    private Integer puntosJ1;

    @Column(name = "puntos_j2", nullable = false)
    private Integer puntosJ2;

    @Column(name = "winner")
    private Byte winner;

    @Column(name = "duracionSeg")
    private Long duracionSeg;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Long getDuracionSeg() {
        return duracionSeg;
    }

    public void setDuracionSeg(Long duracionSeg) {
        this.duracionSeg = duracionSeg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getIdJugador1() {
        return idJugador1;
    }

    public void setIdJugador1(Usuario idJugador1) {
        this.idJugador1 = idJugador1;
    }

    public Usuario getIdJugador2() {
        return idJugador2;
    }

    public void setIdJugador2(Usuario idJugador2) {
        this.idJugador2 = idJugador2;
    }

    public Juego getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(Juego idJuego) {
        this.idJuego = idJuego;
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