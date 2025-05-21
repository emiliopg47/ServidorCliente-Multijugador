package Cliente.Mensajes;

public class CambiarImagenMensaje {
    private byte[] imagen;
    private String nick;
    private String password;

    public CambiarImagenMensaje(){}

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
