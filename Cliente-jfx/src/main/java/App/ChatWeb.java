package App;

import Controler.ChatControler;
import Listener.ChatListener;
import org.json.JSONObject;

import javax.websocket.ClientEndpoint;
import javax.websocket.Session;

@ClientEndpoint
public class ChatWeb extends WebSocketClient {

    private ChatListener listener;


    @Override
    protected void handleMessage(String message) {
        JSONObject json = new JSONObject(message);
        String type = json.getString("type");

        if (type.equals("chat")) {
            JSONObject data = json.getJSONObject("data");
            String user = data.getString("user");
            String content = data.getString("mensaje");

            if (listener != null){
                listener.onMessageReceived(user + ": " + content);
            } else {
                System.out.println("Listener is not set. Message: " + user + ": " + content);
            }

        } else {
            System.out.println("Unknown message type: " + type);
        }
    }

    public void sendChatMessage(String user, String content) {
        JSONObject json = new JSONObject();
        json.put("type", "chat");
        json.put("data", new JSONObject()
                .put("user", user)
                .put("mensaje", content));
        sendMessage(json.toString());
    }

    public void conectar(String nick) {
        String uri = "ws://localhost:8080/ws/chat?nick=" + nick + "&elo=100";
        establecerConexion(uri);
    }

    public void setListener(ChatListener listener) {
        this.listener = listener;
    }
}