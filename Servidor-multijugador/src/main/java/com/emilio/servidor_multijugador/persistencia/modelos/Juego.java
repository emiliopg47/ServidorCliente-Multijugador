package com.emilio.servidor_multijugador.persistencia.modelos;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "juego")
public class Juego {
    @Id
    @Column(name = "id_juego", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @Lob
    @Column(name = "como_jugar")
    private String como_jugar;

    @OneToMany(mappedBy = "idJuego")
    private Set<HistorialGames> historialGames = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idJuego")
    private Set<Ranking> rankings = new LinkedHashSet<>();

    public Set<Ranking> getRankings() {
        return rankings;
    }

    public void setRankings(Set<Ranking> rankings) {
        this.rankings = rankings;
    }

    public Set<HistorialGames> getHistorialGames() {
        return historialGames;
    }

    public void setHistorialGames(Set<HistorialGames> historialGames) {
        this.historialGames = historialGames;
    }

    public String getComo_jugar() {
        return como_jugar;
    }

    public void setComo_jugar(String comoJugar) {
        this.como_jugar = comoJugar;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

}