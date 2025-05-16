package Config;

import java.util.ArrayList;
import java.util.List;

public class CONFIG {
    public static String direccionServidor = "localhost";
    public static double anchoVentanaPong = 600;
    public static double altoVentanaPong = 400;
    public static double altoPala = 60;
    public static double anchoPala = 10;

    public static double xPalaIzquierda = 30;
    public static double xPalaDerecha = 560;

    public static List<String> listaAplicaciones = new ArrayList<>() {{;
        add("Pong");
        add("Chat");
    }};

    public static List<String> listaJuegos = new ArrayList<>() {{;
        add("Pong");
    }};



}
