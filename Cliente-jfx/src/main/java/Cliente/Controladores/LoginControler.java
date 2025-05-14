package Cliente.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LoginControler extends Controler {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin() {
        try {
            String user = usernameField.getText();
            String pass = passwordField.getText();
            if (user.isEmpty() || pass.isEmpty()) {
                System.out.println("Por favor, completa todos los campos.");
                return;
            }
            JSONObject json = new JSONObject();
            json.put("nick", user);
            json.put("password", pass);

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(APIREQUEST.LOGIN_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonResponse = new JSONObject(response.body());
            boolean success = jsonResponse.getBoolean("success");

            if (success){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PrincipalApp.fxml"));
                Parent root = loader.load();
                Stage stage = getStage(loader, user, root);

                stage.show();
            } else {
                showError("Error de inicio de sesión.", jsonResponse.getString("message"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Stage getStage(FXMLLoader loader, String user, Parent root) {
        PrincipalControler principalControler = loader.getController();
        principalControler.setNick(user);
        Scene scene = new Scene(root);

        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Aplicación Principal");
        stage.setWidth(1000); // Establecer un tamaño máximo de ancho
        stage.setHeight(800); // Establecer un tamaño máximo de altura
        stage.centerOnScreen();
        return stage;
    }

}