package com.emilio.servidor_multijugador.web.Mensajes;

public class PongMove {
    String direccion;
    String paddle;

    public PongMove(String direccion, String paddle) {
        this.direccion = direccion;
        this.paddle = paddle;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPaddle() {
        return paddle;
    }

    public void setPaddle(String paddle) {
        this.paddle = paddle;
    }
}
