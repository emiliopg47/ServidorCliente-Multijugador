package com.emilio.servidor_multijugador.persistencia.modelos;

import jakarta.persistence.*;

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
    @Column(name = "comoJugar")
    private String comoJugar;

    public String getComoJugar() {
        return comoJugar;
    }

    public void setComoJugar(String comoJugar) {
        this.comoJugar = comoJugar;
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