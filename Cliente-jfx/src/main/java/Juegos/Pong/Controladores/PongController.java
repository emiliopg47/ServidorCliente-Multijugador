package Juegos.Pong.Controladores;

import Juegos.Pong.LoopJuego;
import Juegos.Pong.Modelos.Game;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.AnchorPane;

import java.util.HashSet;
import java.util.Set;

public class PongController {

    @FXML
    private AnchorPane anchorPane;

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


    @FXML
    public void initialize() {
        Game estado = new Game(palaDerecha.getLayoutX(), palaIzquierda.getLayoutX(), bola.getLayoutX(), bola.getLayoutY());
        LoopJuego loop = new LoopJuego(estado, this);
        loop.start();
        // Configuración inicial de las palas y la bola
        Platform.runLater(() -> {
            Scene scene = anchorPane.getScene();
            if (scene == null) return;

            // Registrar eventos de teclado
            scene.setOnKeyPressed(event -> teclasActivas.add(event.getCode()));  // Agregar tecla presionada
            scene.setOnKeyReleased(event -> teclasActivas.remove(event.getCode()));  // Eliminar tecla liberada

            // Forzar el foco al AnchorPane
            anchorPane.requestFocus();
        });
    }


    public void actualizar(Game game){
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

    public Set<KeyCode> getTeclasActivas() {
        return teclasActivas;
    }
}
