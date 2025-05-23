package Cliente.Controladores;

import Cliente.Mensajes.CambiarImagenMensaje;
import Cliente.Respuestas.CambioFotoPerfilResponse;
import Config.APIREQUEST;
import Config.CONFIG;
import Config.UsuarioLogeado;
import Util.JsonUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;

import java.net.URI;
public class InfoPerfilController extends Controller{
    @FXML
    private ImageView imgViewPerfil;

    @FXML
    private Image imagenPerfil;

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
        recuperarInformacionPerfil();
        gameComboBox.getItems().addAll(CONFIG.listaJuegos);
    }


    public void recuperarInformacionPerfil(){
        nombreLabel.setText(UsuarioLogeado.nick);
        correoLabel.setText(UsuarioLogeado.correo);
        fechaNacimientoLabel.setText(UsuarioLogeado.fechaNacimiento);

    }

    public void cambiarImagen(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar imagen de perfil");

        // Filtros para solo permitir imágenes
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        // Mostrar diálogo de selección
        File archivoSeleccionado = fileChooser.showOpenDialog(null);

        if (archivoSeleccionado != null) {
            try {
                // Leer archivo como byte[]
                byte[] datosImagen = Files.readAllBytes(archivoSeleccionado.toPath());
                CambiarImagenMensaje cambiarImagenMensaje = new CambiarImagenMensaje();
                cambiarImagenMensaje.setNick(UsuarioLogeado.nick);
                cambiarImagenMensaje.setPassword(UsuarioLogeado.password);
                cambiarImagenMensaje.setImagen(datosImagen);

                String json = JsonUtils.toJson(cambiarImagenMensaje);

                CambioFotoPerfilResponse resp = postCambiarImagen(json);

                if (resp.isSuccess()) {
                    // Actualizar la imagen de perfil en la interfaz
                    byte[] foto = resp.getNuevaFotoPerfil();
                    UsuarioLogeado.imagenPerfil = foto;
                    if (foto != null) {
                        imagenPerfil = new Image(new ByteArrayInputStream(foto));
                    } else {
                        showError("Error: ", "No se pudo cargar la imagen.");
                    }
                } else {
                    showError("Error: ", resp.message);
                }


            } catch (IOException e) {
                e.printStackTrace();
                showError("Error: ","Error al leer la imagen.");
            }
        } else {
            System.out.println("No se seleccionó ningún archivo.");
        }
    }

    public CambioFotoPerfilResponse postCambiarImagen(String json) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(APIREQUEST.CAMBIAR_IMAGEN))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonResponse = new JSONObject(response.body());
            return JsonUtils.fromJson(jsonResponse.toString(), CambioFotoPerfilResponse.class);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }

    }

}
