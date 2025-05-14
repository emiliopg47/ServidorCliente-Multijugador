package Cliente.Controladores;

import Cliente.Conexion.ChatClient;
import Cliente.Conexion.PongClient;
import Juegos.Pong.Controladores.PongController;
import Util.CONFIG;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PrincipalControler {

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

            currentStage.setOnCloseRequest(e -> pongClient.close());
            currentStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    public void handleViewProfile() {
        System.out.println("Mostrando perfil...");
    }


    @FXML
    public void handleLogout() {
        System.out.println("Cerrando sesión...");
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


