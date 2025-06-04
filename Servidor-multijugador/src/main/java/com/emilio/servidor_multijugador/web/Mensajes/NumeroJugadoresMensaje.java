package com.emilio.servidor_multijugador.web.Mensajes;

public class NumeroJugadoresMensaje {
    private int numeroJugadores;

    public NumeroJugadoresMensaje(int numeroJugadores) {
        this.numeroJugadores = numeroJugadores;
    }

    public NumeroJugadoresMensaje() {
    }

    public int getNumeroJugadores() {
        return numeroJugadores;
    }

    public void setNumeroJugadores(int numeroJugadores) {
        this.numeroJugadores = numeroJugadores;
    }

}
