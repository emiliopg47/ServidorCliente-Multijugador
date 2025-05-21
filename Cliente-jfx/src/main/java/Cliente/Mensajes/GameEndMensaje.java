package Cliente.Mensajes;
public class GameEndMensaje {
    private String ganador;
    private int puntosJugadorIzquierda;
    private int puntosJugadorDerecha;

    public GameEndMensaje(){}

    public String getGanador() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }

    public int getPuntosJugadorIzquierda() {
        return puntosJugadorIzquierda;
    }

    public void setPuntosJugadorIzquierda(int puntosJugadorIzquierda) {
        this.puntosJugadorIzquierda = puntosJugadorIzquierda;
    }

    public int getPuntosJugadorDerecha() {
        return puntosJugadorDerecha;
    }

    public void setPuntosJugadorDerecha(int puntosJugadorDerecha) {
        this.puntosJugadorDerecha = puntosJugadorDerecha;
    }
}
