package Controler;

import App.ChatWeb;
import App.WebSocketConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;
import java.net.URI;


public class PrincipalControler {

    private String nick;

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNick(){
        return nick;
    }

    @FXML
    public void handleSearchGame() {
        System.out.println("Buscando partida...");
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
        // Abrir el chat pasando el nick
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Chat.fxml"));
            Parent root = loader.load();
            ChatControler chatControler = loader.getController();
            chatControler.setNick(nick); // Pasar el nick al controlador del chat

            WebSocketConnection webSocketConnection = new WebSocketConnection();
            String uri = "ws://localhost:8080/ws/chat?nick=" + nick + "&elo=100";
            ChatWeb cliente = new ChatWeb();
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(cliente, new URI(uri));

            chatControler.setChatWeb(cliente); // Pasar la instancia de ChatWeb al controlador del chat

            Scene scene = new Scene(root);

            Stage stage = new Stage(); // Crear una nueva ventana para el chat
            stage.setScene(scene);
            stage.setTitle("Chat");
            stage.setWidth(1000); // Establecer un tamaño máximo de ancho
            stage.setHeight(800); // Establecer un tamaño máximo de altura
            stage.centerOnScreen();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


