package Cliente.Controladores;

import Cliente.Conexion.ChatClient;
import Cliente.Conexion.PongClient;
import Juegos.Pong.Controladores.PongController;
import Config.CONFIG;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PrincipalController {

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

            currentStage.setOnCloseRequest(e -> closePong(pongClient, event));
            currentStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void closePong(PongClient pongClient, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Principal.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Aplicación Principal");
            stage.setWidth(1000); // Establecer un tamaño máximo de ancho
            stage.setHeight(800); // Establecer un tamaño máximo de altura
            stage.centerOnScreen();

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (pongClient != null) {
            pongClient.close();
        }
    }


    @FXML
    public void handleViewProfile() {
        System.out.println("Mostrando perfil...");
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


