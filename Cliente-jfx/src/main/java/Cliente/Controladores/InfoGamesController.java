package Cliente.Controladores;

import Config.APIREQUEST;
import Config.APP_VARIABLES;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import org.json.JSONObject;


public class InfoGamesController extends Controller{

    @FXML
    public ComboBox<String> gameComboBox;

    @FXML
    public TextArea descripcionArea;

    @FXML
    public TextArea comoJugarArea;

    @FXML
    public void initialize() {
        gameComboBox.getItems().addAll(APP_VARIABLES.listaAplicaciones);
        gameComboBox.setOnAction(event ->    mostrarInformacionJuego(gameComboBox.getValue()));
    }

    public void mostrarInformacionJuego(String juego) {
        JSONObject json = getApi(APIREQUEST.GAME_INFO + "/" + juego);
        System.out.println(json);

        String descripcion =json.getString("descripcion");
        String comoJugar = json.getString("comoJugar");

        if (descripcion == null || descripcion.isEmpty()) {
            descripcion = "No hay descripción disponible.";
        }
        if (comoJugar == null  || comoJugar.isEmpty()) {
            comoJugar = "No hay instrucciones disponibles.";
        }

        descripcionArea.setText(descripcion);
        comoJugarArea.setText(comoJugar);
    }
}
