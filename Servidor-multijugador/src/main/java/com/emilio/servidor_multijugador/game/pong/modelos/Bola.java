package com.emilio.servidor_multijugador.game.pong.modelos;

import java.util.Random;

public class Bola {

    private double x;
    private double y;
    private double dx;
    private double dy;
    private double radio;
    private double velocidad;
    private double aceleracion;
    private double velocidadMaxima = 10;

    public Bola(double x, double y) {
        this.x = x;
        this.y = y;
        this.radio = 10;
        this.aceleracion = 0.15;
        this.velocidad = 2;
        double[] direccion = calcularDireccionUnitario();
        this.dx = direccion[0];
        this.dy = direccion[1];
    }

    public Bola() {
        this(300, 200);
    }

    public void mover() {
        x += dx * velocidad;
        y += dy * velocidad;
    }

    public void reset() {
        double[] direccion = calcularDireccionUnitario();
        this.dx = direccion[0];
        this.dy = direccion[1];
        this.velocidad = calcularVelocidadReset(this.velocidad, 2, velocidadMaxima);
        this.x = 300;
        this.y = 200;
    }

    public static double calcularVelocidadReset(double velocidadActual, double base, double max) {
        double nuevaVelocidad = base + (velocidadActual - base) * 0.5;
        return Math.min(nuevaVelocidad, max);
    }

    public static double[] calcularDireccionUnitario() {
        Random r = new Random();
        double[] rangos = {
                Math.PI / 6, Math.PI / 3,          // 30° - 60°
                2 * Math.PI / 3, 5 * Math.PI / 6,  // 120° - 150°
                7 * Math.PI / 6, 4 * Math.PI / 3,  // 210° - 240°
                5 * Math.PI / 3, 11 * Math.PI / 6  // 300° - 330°
        };

        // Elegir un par de rangos al azar (par de índices)
        int sector = r.nextInt(4) * 2;
        double min = rangos[sector];
        double max = rangos[sector + 1];

        double angle = min + (max - min) * r.nextDouble();

        double dx = Math.cos(angle);
        double dy = Math.sin(angle);

        return new double[]{dx, dy};
    }


    // Getters y Setters
    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = Math.min(velocidad, velocidadMaxima);
    }

    public double getAceleracion() {
        return aceleracion;
    }

    public void setAceleracion(double aceleracion) {
        this.aceleracion = aceleracion;
    }

    public double getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public void setVelocidadMaxima(double velocidadMaxima) {
        this.velocidadMaxima = velocidadMaxima;
    }

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}

