package Cliente;

import Config.CONFIG;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class GUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Properties props = new Properties();
            File configFile = new File("config.properties");

            if (configFile.exists()) {
                props.load(new FileInputStream(configFile));
                CONFIG.tipoArranque = "Servidor Google Cloud";
            } else {
                props.load(getClass().getResourceAsStream("/config.example.properties"));
                CONFIG.tipoArranque = "Servidor Local";
            }

            CONFIG.direccionServidor = props.getProperty("server.ip");

        } catch (Exception e) {
            System.err.println("Error cargando configuración: " + e.getMessage());
        }



        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/logoApp.png")));
        primaryStage.setTitle("Login JavaFX");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}