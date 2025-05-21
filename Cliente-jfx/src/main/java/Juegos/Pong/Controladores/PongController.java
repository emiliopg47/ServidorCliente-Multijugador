package Juegos.Pong.Controladores;

import Cliente.Conexion.PongClient;
import Cliente.Controladores.Controller;
import Cliente.Controladores.PrincipalController;
import Cliente.Mensajes.GameEndMensaje;
import Cliente.Mensajes.GameStateMensaje;
import Juegos.Pong.LoopJuego;
import Juegos.Pong.Modelos.GameState;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashSet;
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

    private final Set<KeyCode> teclasActivas = new HashSet<>();

    boolean gameStarted = false;
    PongClient pongClient;

    private String nick;

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
        LoopJuego loopJuego = new LoopJuego(pongClient, this);
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

    public void actualizar(GameStateMensaje game){
        // Actualiza la posición de las palas
        palaIzquierda.setLayoutY(game.getPalaIzquierda());
        palaDerecha.setLayoutY(game.getPalaDerecha());
        // Actualiza la posición de la bola
        bola.setLayoutX(game.getxBola());
        bola.setLayoutY(game.getyBola());
        // Actualiza el marcador
        marcadorIzq.setText(String.valueOf(game.getMarcadorIzquierda()));
        marcadorDrch.setText(String.valueOf(game.getMarcadorDerecha()));
        // Actualiza el estado del juego
    }

    public void mostrarFinJuego(GameEndMensaje gameEndMensaje) {
        // Mostrar caja de mensaje con el resultado
        String mensaje = "Fin del juego\n";
        mensaje += "Marcador final:\n";
        if (gameEndMensaje.getGanador().equals("IZQUIERDA")){
            mensaje += "Ganador: Jugador 1\n";
            mensaje += "Jugador1: " + gameEndMensaje.getPuntosJugadorIzquierda() + "\n";
            mensaje += "Jugador2: " + gameEndMensaje.getPuntosJugadorDerecha() + "\n";
        }
        if (gameEndMensaje.getGanador().equals("DERECHA")){
            mensaje += "Ganador: Jugador 2\n";
            mensaje += "Jugador1: " + gameEndMensaje.getPuntosJugadorIzquierda() + "\n";
            mensaje += "Jugador2: " + gameEndMensaje.getPuntosJugadorDerecha() + "\n";
        }
        showInformation("FIN DEL JUEGO", mensaje);
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
}
