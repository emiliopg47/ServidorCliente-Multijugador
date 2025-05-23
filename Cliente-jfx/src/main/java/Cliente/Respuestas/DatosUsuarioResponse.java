package Cliente.Respuestas;

import Cliente.Respuestas.modelos.Usuario;

public class DatosUsuarioResponse {
    private boolean success;
    private String message;
    private Usuario usuario;

    public DatosUsuarioResponse(boolean success, String message, Usuario usuario) {
        this.success = success;
        this.message = message;
        this.usuario = usuario;
    }

    public DatosUsuarioResponse() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
