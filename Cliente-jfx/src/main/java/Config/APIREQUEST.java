package Config;

public class APIREQUEST {
    public static final String API_URL = "http://" + CONFIG.direccionServidor + ":8080/api";
    public static final String LOGIN_URL = API_URL + "/login";
    public static final String REGISTER_URL = API_URL + "/register";

    public static final String GAME_INFO = API_URL + "/infoJuego";


    public static final String CAMBIAR_IMAGEN = API_URL + "/cambiarFotoPerfil";

    public static final String ELO = API_URL + "/elo";
    public static final String HISTORIAL_URL = API_URL + "/historial";
}
