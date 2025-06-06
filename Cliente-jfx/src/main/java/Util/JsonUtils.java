package Util;

import Cliente.Mensajes.MensajeGeneral;
import Cliente.Mensajes.PlayerMensaje;
import Cliente.Respuestas.modelos.HistorialGameDTO;
import Cliente.Respuestas.modelos.TopRanking;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

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

    public static String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir objeto a JSON", e);
        }
    }

    public static List<PlayerMensaje> fromJsonListPlayer(String dataString, Class<PlayerMensaje> playerMensajeClass) {
        try {
            return mapper.readValue(dataString, mapper.getTypeFactory().constructCollectionType(List.class, playerMensajeClass));
        } catch (Exception e) {
            throw new RuntimeException("Error parsing JSON list", e);
        }
    }

    public static List<HistorialGameDTO> fromJsonListHistorial(String dataString, Class<HistorialGameDTO> historialGameDTOClass) {
        try {
            return mapper.readValue(dataString, mapper.getTypeFactory().constructCollectionType(List.class, historialGameDTOClass));
        } catch (Exception e) {
            throw new RuntimeException("Error parsing JSON list", e);
        }
    }

    public  static  List<TopRanking> fromJsonListRanking(String dataString, Class<TopRanking> rankingClass) {
        try {
            return mapper.readValue(dataString, mapper.getTypeFactory().constructCollectionType(List.class, rankingClass));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing JSON list", e);
        }
    }
}
