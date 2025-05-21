package com.emilio.servidor_multijugador.web.Mensajes;

public class CambioFotoPerfilResponse {
    public boolean success;
    public String message;
    public byte[] nuevaFotoPerfil;

    public CambioFotoPerfilResponse(boolean success, String message, byte[] nuevaFotoPerfil) {
        this.success = success;
        this.message = message;
        this.nuevaFotoPerfil = nuevaFotoPerfil;
    }

    public CambioFotoPerfilResponse(){}
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public byte[] getNuevaFotoPerfil() {
        return nuevaFotoPerfil;
    }

    public void setNuevaFotoPerfil(byte[] nuevaFotoPerfil) {
        this.nuevaFotoPerfil = nuevaFotoPerfil;
    }
}
