package Cliente.Controladores;

import Util.CONFIG;

public class APIREQUEST {
    public static final String API_URL = "http://" + CONFIG.direccionServidor + ":8080/api";
    public static final String LOGIN_URL = API_URL + "/login";
    public static final String REGISTER_URL = API_URL + "/register";
    //public static final String LOGOUT_URL = API_URL + "/elo";

}
