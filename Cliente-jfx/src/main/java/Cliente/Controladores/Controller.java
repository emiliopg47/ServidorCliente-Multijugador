package Cliente.Controladores;

import Config.CONFIG;
import javafx.scene.control.Alert;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Controller {
    public void showError(String cabezera, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error: ");
        alert.setHeaderText(cabezera);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void showConfirmation(String cabezera, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación: ");
        alert.setHeaderText(cabezera);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void showInformation(String cabezera, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información: ");
        alert.setHeaderText(cabezera);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public JSONObject postApi(String url, String json) {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        return getJsonObject(client, request);
    }

    public JSONObject getApi(String url) {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        return getJsonObject(client, request);
    }

    private JSONObject getJsonObject(HttpClient client, HttpRequest request) {
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            showError("Error de conexión", "No se pudo conectar con el servidor. Se esta conectando a: " + CONFIG.tipoArranque);
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new JSONObject(response.body());
    }
}
