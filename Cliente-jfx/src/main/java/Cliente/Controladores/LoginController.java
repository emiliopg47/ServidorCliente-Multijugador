package Cliente.Controladores;

import Cliente.Respuestas.DatosUsuarioResponse;
import Config.APIREQUEST;
import Config.UsuarioLogeado;
import Util.JsonUtils;
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

public class LoginController extends Controller {

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

            JSONObject jsonResponse = postApi(APIREQUEST.LOGIN_URL,json.toString());
            DatosUsuarioResponse respuesta = JsonUtils.fromJson(jsonResponse.toString(), DatosUsuarioResponse.class);

            if (respuesta.isSuccess()) {
                UsuarioLogeado.nick = respuesta.getUsuario().getNick();
                UsuarioLogeado.correo = respuesta.getUsuario().getCorreo();
                UsuarioLogeado.password = respuesta.getUsuario().getPassword();
                UsuarioLogeado.fechaNacimiento = respuesta.getUsuario().getFechaNac();
                UsuarioLogeado.imagenPerfil = respuesta.getUsuario().getImagen();
                UsuarioLogeado.elo = obtenerElo(respuesta.getUsuario().getNick());

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

    @FXML
    public void abrirRegistro() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Registro.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Registro");
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Stage getStage(FXMLLoader loader, String user, Parent root) {
        PrincipalController principalControler = loader.getController();
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

    private int obtenerElo(String nick) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(APIREQUEST.ELO + "/" + nick + "/pmilong"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonResponse = new JSONObject(response.body());
            if (jsonResponse.getBoolean("success")) {
                return jsonResponse.getInt("elo");
            } else {
                System.out.println("Error al obtener el Elo: " + jsonResponse.getString("message"));
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}