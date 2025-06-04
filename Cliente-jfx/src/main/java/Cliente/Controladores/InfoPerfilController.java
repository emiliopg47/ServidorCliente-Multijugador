package Cliente.Controladores;

import Cliente.Mensajes.CambiarImagenMensaje;
import Cliente.Respuestas.CambioFotoPerfilResponse;
import Cliente.Respuestas.modelos.HistorialGameDTO;
import Config.APIREQUEST;
import Config.APP_VARIABLES;
import Config.UsuarioLogeado;
import Util.JsonUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
import java.util.List;

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
        gameComboBox.getItems().addAll(APP_VARIABLES.listaJuegos);
        cargarHistorial();
    }


    public void recuperarInformacionPerfil(){
        nombreLabel.setText(UsuarioLogeado.nick);
        correoLabel.setText(UsuarioLogeado.correo);
        fechaNacimientoLabel.setText(UsuarioLogeado.fechaNacimiento);
        if (UsuarioLogeado.imagenPerfil != null) {
            imagenPerfil = new Image(new ByteArrayInputStream(UsuarioLogeado.imagenPerfil));
            imgViewPerfil.setImage(imagenPerfil);
        } else {
            // Si no hay imagen, se puede establecer una imagen por defecto
            imgViewPerfil.setImage(UsuarioLogeado.getFxImage());
        }
        puntuacionLabel.setText(String.valueOf(UsuarioLogeado.elo));

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



    public void cargarHistorial(){
        JSONObject json = getApi(APIREQUEST.HISTORIAL_URL + "/" + UsuarioLogeado.nick);

        historialListView.setCellFactory(lv -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    if (item.contains("Win" )) {
                        setStyle("-fx-background-color: lightgreen;");
                    } else if (item.contains("Lose" )) {
                        setStyle("-fx-background-color: lightcoral;");
                    } else {
                        setStyle("");
                    }
                }
            }
        });

        if (json.getBoolean("success")) {
            List<HistorialGameDTO> historial = JsonUtils.fromJsonListHistorial(json.getJSONArray("historialGameDTO").toString(), HistorialGameDTO.class);
            historialListView.getItems().clear();
            for (HistorialGameDTO game : historial) {
                if (game.getIdJugador1Nick().equals(UsuarioLogeado.nick)) {
                    String resultado = (game.getWinner() == 1) ? "Win" : "Lose";
                    historialListView.getItems().add(
                            resultado + ": " + game.getIdJugador1Nick() + " vs " + game.getIdJugador2Nick() +
                                    " | " + game.getPuntosJ1() + " | " + game.getDuracionSeg() + "seg | " + game.getFechaHora()
                    );
                } else if (game.getIdJugador2Nick().equals(UsuarioLogeado.nick)) {
                    String resultado = (game.getWinner() == 2) ? "Win" : "Lose";
                    historialListView.getItems().add(
                            resultado + ": " + game.getIdJugador1Nick() + " vs " + game.getIdJugador2Nick() +
                                    " | " + game.getPuntosJ2() + " | " + game.getDuracionSeg() + "seg | " + game.getFechaHora()
                    );
                }
            }
        } else {
            showError("Error: ", json.getString("message"));
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
