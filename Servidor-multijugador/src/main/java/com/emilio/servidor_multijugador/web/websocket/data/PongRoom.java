package com.emilio.servidor_multijugador.web.websocket.data;

import com.emilio.servidor_multijugador.Util.CONFIG;
import com.emilio.servidor_multijugador.Util.JsonUtils;
import com.emilio.servidor_multijugador.game.pong.modelos.GameState;
import com.emilio.servidor_multijugador.web.Mensajes.GameStateMensaje;
import com.emilio.servidor_multijugador.web.Mensajes.GameEndMensaje;
import com.emilio.servidor_multijugador.web.Mensajes.MensajeGeneral;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PongRoom extends Room {

    private GameState estado;
    private ScheduledExecutorService scheduler;

    public PongRoom(String id) {
        super(id);

    }

    public void comenzarJuego() {
        estado = new GameState();
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        startLoop();
    }

    public void pararJuego() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
        }
    }

    public GameState getEstado() {
        return estado;
    }

    private void startLoop() {
        // 30 FPS
        //long frameTime = 1000 / 30;
        // 60 FPS
        long frameTime = 1000 / 60;

        scheduler.scheduleAtFixedRate(this::loop, 0, frameTime, TimeUnit.MILLISECONDS);
    }

    public void stopLoop() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
        }
    }

    public void loop() {


        // 1. Actualizar lógica del juego
        estado.actualizar();
        // 2. Comprobar colisiones
        estado.comprobarColisiones();
        // 3. Enviar el estado del juego a los jugadores
        broadcastState();
    }
    public void broadcastState() {
        //Comprobar ganador
        if (estado.getMarcadorIzq() == CONFIG.puntosNecesariosParaGanar){
            GameEndMensaje mensajeGanador = new GameEndMensaje();
            mensajeGanador.setGanador("IZQUIERDA");
            mensajeGanador.setPuntosJugadorDerecha(-10);
            mensajeGanador.setPuntosJugadorIzquierda(10);

            mandarMensajeBroadcast(new MensajeGeneral("FIN",mensajeGanador));
            stopLoop();
        }

        if (estado.getMarcadorDrch() == CONFIG.puntosNecesariosParaGanar){
            GameEndMensaje mensajeGanador = new GameEndMensaje();
            mensajeGanador.setGanador("DERECHA");
            mensajeGanador.setPuntosJugadorDerecha(10);
            mensajeGanador.setPuntosJugadorIzquierda(-10);
                mandarMensajeBroadcast(new MensajeGeneral("FIN",mensajeGanador));
            stopLoop();
        }

        GameStateMensaje mensajeEstado = new GameStateMensaje(estado);
        MensajeGeneral mensaje = new MensajeGeneral("ESTADO", mensajeEstado);
        mandarMensajeBroadcast(mensaje);

    }
    private void mandarMensajeBroadcast(MensajeGeneral mensaje){
        for (Player player : getPlayers()) {
            try {
                String json = JsonUtils.toJson(mensaje);
                player.getSession().sendMessage(new TextMessage(json));
            } catch (IOException e) {
                System.out.println("Error al enviar el mensaje a " + player.getNick() + ": " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}