package Cliente.Controladores;

import Cliente.Conexion.ChatClient;
import Cliente.Mensajes.ChatData;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatController extends Controler {


    @FXML
    public TextField mensajeField;

    @FXML
    private TextArea chatArea;

    private String nick;

    private ChatClient chatClient;

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNick(){
        return nick;
    }

    public void setChatClient(ChatClient chatClient) {
        this.chatClient = chatClient;

    }

    public void actualizarChat(ChatData data) {
        chatArea.appendText(data.toString());
    }


    @FXML
    public void enviarMensaje() {
        if (mensajeField == null || mensajeField.getText().isEmpty()) {
            return;
        }
        if (chatClient != null) {
            chatClient.enviarMensajeChat(mensajeField.getText());
        } else {
            showError("Error", "No se ha establecido la conexión con el servidor.");
        }
    }


    public void cerrarChat() {
        if (chatClient != null) {
            chatClient.close();
        }
    }

}
