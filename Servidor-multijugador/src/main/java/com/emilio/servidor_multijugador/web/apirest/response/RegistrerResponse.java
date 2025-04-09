package com.emilio.servidor_multijugador.web.apirest.response;

public class RegistrerResponse {
    private boolean success;
    private String message;

    public RegistrerResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
