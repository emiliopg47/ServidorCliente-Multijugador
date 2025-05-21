package Cliente.Controladores;

import Cliente.Conexion.ChatClient;
import Cliente.Conexion.PongClient;
import Config.CONFIG;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;


public class PrincipalController extends Controller {

    // CABECERA
    @FXML
    private ImageView imgPerfil;
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
    private ImageView imgAvatarCentro;
    @FXML
    private Label lblNombreCentro;
    @FXML
    private Label lblRangoCentro;
    @FXML
    private Button btnBuscar;

    // CHAT
    @FXML
    private TextArea chatOutput;
    @FXML
    private TextField chatInput;

    // PIE
    @FXML
    private ImageView imgGitHub;

    // Imagen de fondo (añadida por código para poder hacer el bind)
    private ImageView fondoJugar;



    private String nick;

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNick(){
        return nick;
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
            String uri = "ws://" + CONFIG.direccionServidor + ":8080/ws/pong";
            pongClient.conectar(uri);

            // Obtener el stage actual a partir del botón u otro nodo
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            currentStage.setScene(scene);
            currentStage.sizeToScene();
            currentStage.centerOnScreen();
            currentStage.setTitle("Pong");
            currentStage.setResizable(false);


            currentStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    public void handleViewGitHub() {
        /*String url = "https://github.com/emiliopg47/ServidorCliente-Multijugador";

        try {
            if (java.awt.Desktop.isDesktopSupported()) {
                java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
                if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
                    desktop.browse(java.net.URI.create(url));
                    return;
                }
            }

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
        }*/
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

    @FXML
    public void handleChat() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Chat.fxml"));
            Parent root = loader.load();
            ChatController chatControler = loader.getController();
            chatControler.setNick(nick);

            ChatClient chatClient = new ChatClient(nick,chatControler);
            chatControler.setChatClient(chatClient);
            String uri = "ws://" + CONFIG.direccionServidor +  ":8080/ws/chat?nick=" + nick + "&elo=100";
            chatClient.conectar(uri); // Establecer la conexión al chat


            Scene scene = new Scene(root);

            Stage stage = new Stage(); // Crear una nueva ventana para el chat
            stage.setScene(scene);
            stage.setTitle("Chat");
            stage.setWidth(600); // Establecer un tamaño máximo de ancho
            stage.setHeight(300); // Establecer un tamaño máximo de altura
            stage.centerOnScreen();

            stage.setOnCloseRequest(event -> {
                chatControler.cerrarChat();
            });

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


