package Util;

import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;

public class Funciones {

    public static Image fromByteArrayToImage(byte[] imageData) {
        if (imageData == null || imageData.length == 0) {
            return null;
        }
        return new Image(new ByteArrayInputStream(imageData));
    }
}
