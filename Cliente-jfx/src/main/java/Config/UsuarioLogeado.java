package Config;

import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;

public  class UsuarioLogeado {
    public static String nick;
    public static String correo;
    public static String fechaNacimiento;
    public static byte[] imagenPerfil;
    public static String password;
    public static int elo;

    public  static Image getFxImage() {
        if (imagenPerfil != null) {
            return new Image(new ByteArrayInputStream(imagenPerfil));
        } else {
            return new Image(UsuarioLogeado.class.getResourceAsStream("/images/fotoPerfilGenerica.png"));
        }
    }

}
