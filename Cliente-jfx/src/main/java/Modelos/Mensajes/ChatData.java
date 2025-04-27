package Modelos.Mensajes;

public class ChatData {
    private String mensaje;
    private String nick;
    private String fecha;

    public ChatData(String mensaje, String usuario, String fecha) {
        this.mensaje = mensaje;
        this.nick = usuario;
        this.fecha = fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "("+ getFecha() + ") " + getNick() + ": " + getMensaje() + "\n";
    }
}
