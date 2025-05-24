package com.emilio.servidor_multijugador.web.apirest.response;

public class EloResponse {
    private boolean success;
    private String message;
    private int elo;

    public EloResponse(boolean success, String message, int ranking) {
        this.success = success;
        this.message = message;
        this.elo = ranking;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public int getElo() {
        return elo;
    }
}
