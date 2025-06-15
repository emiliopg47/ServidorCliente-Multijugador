package Juegos.Pong.Controladores;

import Cliente.Conexion.PongClient;
import Cliente.Controladores.Controller;
import Cliente.Controladores.PrincipalController;
import Cliente.Mensajes.GameEndMensaje;
import Cliente.Mensajes.GameStateMensaje;
import Cliente.Mensajes.PlayerMensaje;
import Config.UsuarioLogeado;
import Juegos.Pong.LoopJuego;
import Juegos.Pong.Modelos.GameState;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PongController extends Controller {

    @FXML
    public AnchorPane anchorPane;

    @FXML
    private Rectangle palaIzquierda;

    @FXML
    private Rectangle palaDerecha;
    @FXML
    private Circle bola;
    @FXML
    private Label marcadorIzq;
    @FXML
    private Label marcadorDrch;

    @FXML
    private Circle fotoPerfilJ2;

    @FXML
    private Circle fotoPerfilJ1;

    private final Set<KeyCode> teclasActivas = new HashSet<>();

    boolean gameStarted = false;
    PongClient pongClient;

    private String nick;

    private int idPlayer;

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            if (anchorPane.getScene() == null) return;
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            stage.setOnCloseRequest(e -> closePong());
            anchorPane.getScene().setOnKeyPressed(event -> teclasActivas.add(event.getCode()));
            anchorPane.getScene().setOnKeyReleased(event -> teclasActivas.remove(event.getCode()));

            anchorPane.requestFocus();
        });
    }


    public void closePong() {
        if (pongClient != null) {
            pongClient.close();
        }
        try {
            Stage oldStage = (Stage) anchorPane.getScene().getWindow();
            oldStage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PrincipalApp.fxml"));
            Parent root = loader.load();
            PrincipalController controller = loader.getController();
            controller.setNick(nick);

            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.setTitle("Aplicación Principal");
            newStage.sizeToScene();
            newStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void startGame() {
        LoopJuego loopJuego = new LoopJuego(pongClient, this, idPlayer);
        loopJuego.start();
        gameStarted = true;
    }

    public boolean isStarted() {
        return gameStarted;
    }

    public void actualizar(GameState game){
        // Actualiza la posición de las palas
        palaIzquierda.setLayoutY(game.getPalaIzquierda().getY());
        palaDerecha.setLayoutY(game.getPalaDerecha().getY());

        // Actualiza la posición de la bola
        bola.setLayoutX(game.getBola().getX());
        bola.setLayoutY(game.getBola().getY());

        // Actualiza el marcador
        marcadorIzq.setText(String.valueOf(game.getMarcadorIzq()));
        marcadorDrch.setText(String.valueOf(game.getMarcadorDrch()));
    }

    public void mostrarFinJuego(GameEndMensaje gameEndMensaje) {
        // Mostrar caja de mensaje con el resultado
        showInformation("FIN DEL JUEGO", gameEndMensaje.getGanador());
        // Cerrar el cliente de Pong
        if (pongClient != null) {
            pongClient.close();
        }
        // Volver a la pantalla principal
        closePong();
    }

    public Set<KeyCode> getTeclasActivas() {
        return teclasActivas;
    }

    public void setPongClient(PongClient pongClient) {
        this.pongClient = pongClient;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void actualizarPlayer(List<PlayerMensaje> playersMensaje) {
        if (playersMensaje.size() >= 2) {
            PlayerMensaje player1 = playersMensaje.get(0);
            PlayerMensaje player2 = playersMensaje.get(1);

            if (player1.getNick().equals(UsuarioLogeado.nick)){
                idPlayer = player1.getId();
            }
            if (player2.getNick().equals(UsuarioLogeado.nick)){
                idPlayer = player2.getId();
            }

            // Actualizar foto de perfil del jugador 1
            if (player1.getFotoPerfil() != null) {
                        try {
                            fotoPerfilJ1.setFill(new ImagePattern(
                                        new Image(new ByteArrayInputStream(player1.getFotoPerfil()))));
                        } catch (Exception e) {
                            fotoPerfilJ1.setFill(new ImagePattern(
                                    new Image(getClass().getResourceAsStream("/images/fotoPerfilGenerica.png"))));
                        }

            }

            // Actualizar foto de perfil del jugador 2
            if (player2.getFotoPerfil() != null) {
                try {
                    fotoPerfilJ2.setFill(new ImagePattern(
                            new Image(new ByteArrayInputStream(player2.getFotoPerfil()))));
                } catch (Exception e) {
                    fotoPerfilJ2.setFill(new ImagePattern(
                            new Image(getClass().getResourceAsStream("/images/fotoPerfilGenerica.png"))));
                }
            }
        }
    }
}
