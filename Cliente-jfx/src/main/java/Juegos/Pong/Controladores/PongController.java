package Juegos.Pong.Controladores;

import Cliente.Conexion.PongClient;
import Cliente.Modelos.Mensajes.GameStateMensaje;
import Juegos.Pong.Modelos.GameState;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.HashSet;
import java.util.Set;

public class PongController {

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
    public GameState estado;

    PongClient pongClient;

    @FXML
    public void initialize() {
        //LoopJuego loop = new LoopJuego(estado, this);
        //loop.start();
        // Configuración inicial de las palas y la bola


/*
        Platform.runLater(() -> {
            if (scene == null) return;

            // Registrar eventos de teclado
            //scene.setOnKeyPressed(event -> teclasActivas.add(event.getCode()));  // Agregar tecla presionada
            //scene.setOnKeyReleased(event -> teclasActivas.remove(event.getCode()));  // Eliminar tecla liberada

            // Forzar el foco al AnchorPane
            anchorPane.requestFocus();
        });

 */

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

    public Set<KeyCode> getTeclasActivas() {
        return teclasActivas;
    }

    public void setPongClient(PongClient pongClient) {
        this.pongClient = pongClient;
    }
}
