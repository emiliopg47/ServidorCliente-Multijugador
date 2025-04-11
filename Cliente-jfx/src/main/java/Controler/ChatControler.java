package Controler;

import App.ChatWeb;
import Listener.ChatListener;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatControler extends Controler implements ChatListener {

    ChatWeb chatWeb;

    @FXML
    public TextField mensajeField;

    @FXML
    private TextArea chatArea;

    private String nick;

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNick(){
        return nick;
    }

    @Override
    public void onMessageReceived(String message) {
        actualizarChat(message);
    }

    public void actualizarChat(String mensaje) {
        chatArea.appendText(mensaje + "\n");
    }

    @FXML
    public void enviarMensaje() {
        if (mensajeField == null || mensajeField.getText().isEmpty()) {
            return;
        }
        chatWeb.sendChatMessage(nick,mensajeField.getText());
    }

    public void setChatWeb(ChatWeb chatWeb) {
        this.chatWeb = chatWeb;
        this.chatWeb.setListener(this);
    }
}
