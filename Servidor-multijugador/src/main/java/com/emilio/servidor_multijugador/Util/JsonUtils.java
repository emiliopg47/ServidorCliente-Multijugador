package com.emilio.servidor_multijugador.Util;

import com.emilio.servidor_multijugador.web.Mensajes.MensajeGeneral;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Error parsing JSON", e);
        }
    }

    public static String toJson(MensajeGeneral mensaje) {
        try {
            return mapper.writeValueAsString(mensaje);
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir objeto a JSON", e);
        }
    }
}
