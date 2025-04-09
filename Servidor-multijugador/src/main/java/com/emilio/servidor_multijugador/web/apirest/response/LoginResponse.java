package com.emilio.servidor_multijugador.web.apirest.response;

import com.emilio.servidor_multijugador.persistencia.modelos.Usuario;

public class LoginResponse {
    private boolean success;
    private String message;
    private Usuario usuario;

    public LoginResponse(boolean success, String message, Usuario usuario) {
        this.success = success;
        this.message = message;
        this.usuario = usuario;
    }
}
