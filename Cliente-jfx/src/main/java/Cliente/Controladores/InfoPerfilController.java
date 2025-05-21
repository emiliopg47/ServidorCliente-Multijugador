package Cliente.Controladores;

import Config.CONFIG;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

public class InfoPerfilController extends Controller{
    @FXML
    private ImageView imagenPerfil;

    @FXML
    private Label nombreLabel;

    @FXML
    private Label correoLabel;

    @FXML
    private Label fechaNacimientoLabel;

    @FXML
    private ComboBox<String> gameComboBox;

    @FXML
    private Label puntuacionLabel;

    @FXML
    private Label historialWLLabel;

    @FXML
    private ListView<String> historialListView;

    @FXML
    private Hyperlink cambiarImagenLink;

    private String nick;

    public void setNick(String nick) {
        this.nick = nick;
    }

    @FXML
    public void initialize() {
        recuperarInformacionPerfil(nick);
        gameComboBox.getItems().addAll(CONFIG.listaJuegos);
    }


    public void recuperarInformacionPerfil(String nick){

    }

}
