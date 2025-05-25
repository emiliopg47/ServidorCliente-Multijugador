package com.emilio.servidor_multijugador.web.apirest.response;

import com.emilio.servidor_multijugador.persistencia.modelos.HistorialGameDTO;

import java.util.List;

public class HistorialResponse {
    private boolean success;
    private String mensaje;
    private List<HistorialGameDTO> HistorialGameDTO;

    public HistorialResponse(){}

    public HistorialResponse(boolean success, String mensaje, List<HistorialGameDTO> HistorialGameDTO) {
        this.success = success;
        this.mensaje = mensaje;
        this.HistorialGameDTO = HistorialGameDTO;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<HistorialGameDTO> getHistorialGameDTO() {
        return HistorialGameDTO;
    }

    public void setHistorialGameDTO(List<HistorialGameDTO> HistorialGameDTO) {
        this.HistorialGameDTO = HistorialGameDTO;
    }
}
