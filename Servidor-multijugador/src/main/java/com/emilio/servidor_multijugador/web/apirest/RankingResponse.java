package com.emilio.servidor_multijugador.web.apirest;

import com.emilio.servidor_multijugador.persistencia.modelos.TopRanking;

import java.util.List;

public class RankingResponse {
    private boolean success;
    private String message;
    List<TopRanking> ranking;
    public RankingResponse(boolean success, String message, List<TopRanking> ranking) {
        this.success = success;
        this.message = message;
        this.ranking = ranking;
    }

    public RankingResponse(){
        // Constructor por defecto necesario para la deserialización
    }

    public List<TopRanking> getRanking() {
        return ranking;
    }

    public void setRanking(List<TopRanking> ranking) {
        this.ranking = ranking;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
