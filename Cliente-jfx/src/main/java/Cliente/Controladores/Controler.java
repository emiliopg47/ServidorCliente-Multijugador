package Cliente.Controladores;

import javafx.scene.control.Alert;

public class Controler {
    public void showError(String cabezera, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error: ");
        alert.setHeaderText(cabezera);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
