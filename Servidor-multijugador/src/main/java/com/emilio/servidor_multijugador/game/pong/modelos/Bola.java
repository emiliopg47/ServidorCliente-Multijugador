package com.emilio.servidor_multijugador.game.pong.modelos;

import static java.lang.Math.random;

public class Bola {

    private double x;
    private double y;
    private double dx;
    private double dy;
    private double radio;
    private double velocidad = 2;
    private double aceleracion;

    public Bola(double x, double y) {
        this.x = x;
        this.y = y;
        this.dx = random() > 0.5 ? 2 : -2;
        this.dy = random() > 0.5 ? 0.5 : -0.5;
        this.radio = 10;
        this.aceleracion = 0.2;
    }

    public Bola(){}

    public void mover() {
        x += dx * velocidad;
        y += dy * velocidad;
    }


    public double getAceleracion() {
        return aceleracion;
    }

    public void setAceleracion(double aceleracion) {
        this.aceleracion = aceleracion;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }
}
