package com.emilio.servidor_multijugador.web.websocket.data;

import com.emilio.servidor_multijugador.Util.CONFIG;
import com.emilio.servidor_multijugador.Util.JsonUtils;
import com.emilio.servidor_multijugador.game.pong.modelos.Bola;
import com.emilio.servidor_multijugador.game.pong.modelos.GameState;
import com.emilio.servidor_multijugador.web.Mensajes.GameStateMensaje;
import com.emilio.servidor_multijugador.web.Mensajes.GameEndMensaje;
import com.emilio.servidor_multijugador.web.Mensajes.MensajeGeneral;
import com.emilio.servidor_multijugador.web.Mensajes.PlayerMensaje;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PongRoom extends Room {

    private GameState estado;
    private ScheduledExecutorService scheduler;

    private int ganador;

    public PongRoom(String id) {
        super(id);

    }

    public void comenzarJuego() {
        List<PlayerMensaje> playerMensajes = new ArrayList<>();
        for (Player player : getPlayers()) {
            PlayerMensaje playerMensaje = new PlayerMensaje();
            playerMensaje.setId(player.getId());
            playerMensaje.setNick(player.getNick());
            playerMensaje.setElo(String.valueOf(player.getElo()));
            playerMensaje.setFotoPerfil(player.getFotoPerfil());
            playerMensajes.add(playerMensaje);
        }
        MensajeGeneral mensajeGeneral = new MensajeGeneral("INFO_GAME", playerMensajes);
        mandarMensajeBroadcast(mensajeGeneral);
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

    public void resetGame() {
        estado.reset();

    }

    public void stopLoop() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
        }
    }


    public void loop() {
        if (estado.comprobarColisiones()) {
            resetGame();
            estado.iniciarPausa(1000); // pausar 1 segundo sin bloquear el hilo
        }

        estado.actualizar();
        broadcastState();
    }


    public void broadcastState() {
        //Comprobar ganador
        if (estado.getMarcadorIzq() == CONFIG.puntosNecesariosParaGanar){
            GameEndMensaje mensajeGanador = new GameEndMensaje();
            mensajeGanador.setGanador("IZQUIERDA");
            mensajeGanador.setPuntosJugadorDerecha(-10);
            mensajeGanador.setPuntosJugadorIzquierda(10);
            ganador = 1;

            mandarMensajeBroadcast(new MensajeGeneral("FIN",mensajeGanador));
            stopLoop();
        }

        if (estado.getMarcadorDrch() == CONFIG.puntosNecesariosParaGanar){
            GameEndMensaje mensajeGanador = new GameEndMensaje();
            mensajeGanador.setGanador("DERECHA");
            mensajeGanador.setPuntosJugadorDerecha(10);
            mensajeGanador.setPuntosJugadorIzquierda(-10);
            mandarMensajeBroadcast(new MensajeGeneral("FIN",mensajeGanador));
            ganador = 2;
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

    public int getGanador() {
        return ganador;
    }
}