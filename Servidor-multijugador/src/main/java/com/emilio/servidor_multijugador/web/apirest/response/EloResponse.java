package com.emilio.servidor_multijugador.web.apirest.response;

import com.emilio.servidor_multijugador.persistencia.modelos.Ranking;

public class EloResponse {
    private boolean success;
    private String message;
    private Long ranking;

    public EloResponse(boolean success, String message, Long ranking) {
        this.success = success;
        this.message = message;
        this.ranking = ranking;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Long getRanking() {
        return ranking;
    }
}
