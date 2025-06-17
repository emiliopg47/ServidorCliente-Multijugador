package Cliente.Controladores;

import Cliente.Conexion.ChatClient;
import Cliente.Conexion.PongClient;
import Cliente.Mensajes.ChatData;
import Config.CONFIG;
import Config.UsuarioLogeado;
import Juegos.Pong.Controladores.PongController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;


public class PrincipalController extends Controller {
    public Circle circleRanking;
    public Label lblJugadoresActivos;

    // CABECERA

    @FXML
    private Circle circleInfPerfil;

    @FXML
    private Circle circleSupPerfil;

    @FXML
    private Button btnInfo;
    @FXML
    private Label lblJugadores;
    @FXML
    private Button btnCerrarSesion;

    // CUADRO CENTRAL - PANEL JUGAR
    @FXML
    private StackPane panelJugar;

    @FXML
    private Label lblNombreCentro;
    @FXML
    private Label lblRangoCentro;
    @FXML
    private Button btnBuscar;

    // CHAT
    @FXML
    private TextArea txtAreaChat;
    @FXML
    private TextField txtEnviarMensaje;

    // PIE
    @FXML
    private ImageView imgGitHub;

    // Imagen de fondo (añadida por código para poder hacer el bind)
    private ImageView fondoJugar;


    private ChatClient chatClient;


    private String nick;

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNick(){
        return nick;
    }

    @FXML
    public void initialize() {
        //Cargar los datos del usuario logueado

        javafx.application.Platform.runLater(() -> {
            Stage stage = (Stage) panelJugar.getScene().getWindow();
            stage.setOnCloseRequest(event -> cerrarChat());
        });
        lblNombreCentro.setText(UsuarioLogeado.nick);

        // Cargar la imagen de perfil del usuario logueado

        Image imagenPerfil = UsuarioLogeado.getFxImage();
        circleInfPerfil.setFill(new ImagePattern(imagenPerfil));
        circleSupPerfil.setFill(new ImagePattern(imagenPerfil));

        lblRangoCentro.setText("Puntos: " + UsuarioLogeado.elo);

        txtEnviarMensaje.setOnAction(event -> enviarMensaje());
        iniciarChat();
    }

    @FXML
    public void handleSearchGame(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Pong.fxml"));
            Parent root = loader.load();

            PongController pongController = loader.getController();
            pongController.setNick(nick);
            PongClient pongClient = new PongClient(nick, pongController);
            pongController.setPongClient(pongClient);
            String uri = "ws://" + CONFIG.direccionServidor + ":8080/ws/pong?nick=" + UsuarioLogeado.nick;
            pongClient.conectar(uri);

            // Obtener el stage actual a partir del botón u otro nodo
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            currentStage.setScene(scene);
            currentStage.sizeToScene();
            currentStage.centerOnScreen();
            currentStage.setTitle("Pong");
            currentStage.setResizable(false);

            cerrarChat();

            currentStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    public void handleViewGitHub() {
        String url = "https://github.com/emiliopg47/ServidorCliente-Multijugador";

        try {
            /*
            if (java.awt.Desktop.isDesktopSupported()) {
                java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
                if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
                    desktop.browse(java.net.URI.create(url));
                    return;
                }
            }*/

            // Alternativas según sistema operativo
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
            } else if (os.contains("mac")) {
                Runtime.getRuntime().exec("open " + url);
            } else if (os.contains("nix") || os.contains("nux")) {
                Runtime.getRuntime().exec("xdg-open " + url);
            } else {
                System.err.println("Sistema operativo no soportado: " + os);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleViewProfile() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InfoPerfil.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Perfil");
            stage.setScene(new Scene(root));
            stage.sizeToScene();
            stage.setResizable(false);
            stage.show();
            InfoPerfilController controller = loader.getController();
            controller.setPrincipalController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleInfoGames(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InfoGames.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Información de Juegos");
            stage.setScene(new Scene(root));
            stage.sizeToScene();
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @FXML
    public void handleLogout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.sizeToScene();
            stage.centerOnScreen();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void iniciarChat(){
        chatClient = new ChatClient(this);
        String uri = "ws://" + CONFIG.direccionServidor + ":8080/ws/chat?nick=" + UsuarioLogeado.nick;
        try {
            chatClient.conectar(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizarChat(ChatData data) {
        txtAreaChat.appendText(data.toString());
    }


    @FXML
    public void enviarMensaje() {
        if (txtEnviarMensaje == null || txtEnviarMensaje.getText().isEmpty()) {
            return;
        }
        if (chatClient != null) {
            chatClient.enviarMensajeChat(txtEnviarMensaje.getText());
            txtEnviarMensaje.clear();
        } else {
            showError("Error", "No se ha establecido la conexión con el servidor.");
        }
    }

    public void actualizarNumeroJugadores(int numeroJugadores) {
        lblJugadoresActivos.setText(String.valueOf(numeroJugadores));
    }


    public void cerrarChat() {
        if (chatClient != null) {
            chatClient.close();
        }
    }

    public void handleOpenRanking(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Ranking.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Ranking");
            stage.setScene(new Scene(root));
            stage.sizeToScene();
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actualizarImagenes(){
        // Actualizar la imagen de perfil en los círculos
        Image imagenPerfil = UsuarioLogeado.getFxImage();
        circleInfPerfil.setFill(new ImagePattern(imagenPerfil));
        circleSupPerfil.setFill(new ImagePattern(imagenPerfil));

    }
}


