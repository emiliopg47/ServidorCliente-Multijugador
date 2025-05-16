package Cliente.Controladores;

import Cliente.Mensajes.RegistroMensaje;
import Config.APIREQUEST;
import Util.JsonUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RegistroController extends  Controller{
    @FXML
    private TextField nombreUser;

    @FXML
    private TextField email;

    @FXML
    private PasswordField passwordField;


    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private DatePicker fechaNac;

    @FXML
    public void initialize() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        fechaNac.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate date) {
                return (date != null) ? formatter.format(date) : "";
            }

            @Override
            public LocalDate fromString(String string) {
                return (string != null && !string.isEmpty()) ? LocalDate.parse(string, formatter) : null;
            }
        });

    }
    @FXML
    public void handleRegistro() {
         //Comprobar que los campos no están vacíos
        if (nombreUser.getText().isEmpty() || email.getText().isEmpty() || passwordField.getText().isEmpty() || confirmPasswordField.getText().isEmpty()) {
            showError("Error de registro.", "Por favor, completa todos los campos.");
            return;
        }

        RegistroMensaje registroMensaje = new RegistroMensaje();
        registroMensaje.setNick(nombreUser.getText());
        registroMensaje.setCorreo(email.getText());
        registroMensaje.setPassword(passwordField.getText());
        registroMensaje.setFechaNac(fechaNac.getValue().toString());

        if (!passwordField.getText().equals(confirmPasswordField.getText())) {
            showError("Error de registro.", "Las contraseñas no coinciden.");
            return;
        }

        String json = JsonUtils.toJson(registroMensaje);
        JSONObject respuesta = postApi(APIREQUEST.REGISTER_URL, json);
        boolean success = respuesta.getBoolean("success");

        if (success) {
            showConfirmation("",respuesta.getString("message") + ". Redirigiendo a la pantalla de inicio de sesión.");
            registroExitoso();
        } else {
            showError("Error de registro.", respuesta.getString("message"));
        }

    }

    public void registroExitoso(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) nombreUser.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
