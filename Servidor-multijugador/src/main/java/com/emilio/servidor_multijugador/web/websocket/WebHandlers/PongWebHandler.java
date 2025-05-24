package com.emilio.servidor_multijugador.web.websocket.WebHandlers;

import com.emilio.servidor_multijugador.persistencia.modelos.Usuario;
import com.emilio.servidor_multijugador.persistencia.servicios.ServiceRanking;
import com.emilio.servidor_multijugador.persistencia.servicios.ServiceUsuario;
import com.emilio.servidor_multijugador.web.websocket.data.Player;
import com.emilio.servidor_multijugador.web.websocket.data.PongRoom;
import com.emilio.servidor_multijugador.web.websocket.handler.DataHandler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class PongWebHandler extends TextWebSocketHandler {
    Map<String, PongRoom> rooms;
    private static int roomCounter = 1;

    @Autowired
    private ServiceUsuario serviceUsuario;

    @Autowired
    private ServiceRanking serviceRanking;


    public PongWebHandler() {
        this.rooms = new HashMap<>();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);

        Map<String, String> params = UriComponentsBuilder
                .fromUri(session.getUri())
                .build()
                .getQueryParams()
                .toSingleValueMap();

        String nickValue = params.get("nick");


        //Creamos el objeto player con sus datos para pasarlos posteriormente al cliente
        Usuario usuario = serviceUsuario.findByNick(nickValue);
        Player player = new Player();
        player.setSession(session);
        player.setNick(nickValue);
        player.setFotoPerfil(usuario.getImagen());
        player.setElo(serviceRanking.getElo(nickValue));

        // Añadimos el jugador a la sala
        PongRoom room = buscarSalaConJugador();
        if (room == null) {
            player.setId(1);
            room = nuevaSala();
            room.addPlayer(player);
            //startGame(room);  //Solo pruebas
        } else {
            player.setId(2);
            room.addPlayer(player);
            System.out.println("Un jugador se ha unido a la sala: " + room.getId());
            startGame(room);
        }

        // Una vez que esta en sala se le manda el objeto player al cliente


    }
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(message.getPayload());
        String type = root.get("type").asText();
        JsonNode data = root.get("data");

        DataHandler dataHandler = new DataHandler();
        String mensaje = null;
        switch (type){
            case "MOVE_PADDLE":
                dataHandler.movePaddle(data, buscarMiSala(session).getEstado());
                break;
        }
    }

    public void mandarMensaje(String mensaje, WebSocketSession session) {
        PongRoom room = buscarMiSala(session);
        if (room != null) {
            for (Player p: room.getPlayers()){
                if (p.getSession().equals(session)){
                    if (mensaje != null) {
                        room.mandarMensaje(mensaje, session);
                    }
                }
            }
        }
    }
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("Error en la conexión: " + exception.getMessage());
        super.handleTransportError(session, exception);
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("Cliente desconectado: " + session.getId());
        PongRoom room = buscarMiSala(session);
        room.pararJuego();

        //Calcular el elo del jugador




        super.afterConnectionClosed(session, status);
    }

    private PongRoom nuevaSala(){
        System.out.println("No existe sala, creando una nueva");
        PongRoom room = new PongRoom("Sala " + roomCounter++);
        rooms.put(room.getId(), room);
        return room;
    }
    private void startGame(PongRoom room) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        room.comenzarJuego();

    }
    private PongRoom buscarMiSala(WebSocketSession session){
        for (PongRoom room : rooms.values()) {
            for (Player player: room.getPlayers()) {
                if (player.getSession().equals(session)) {
                    return room;
                }
            }
        }
        return null;
    }
    private PongRoom buscarSalaConJugador(){
        for (PongRoom room : rooms.values()) {
            if (room.getPlayers().size() < 2) {
                return room;
            }
        }
        return null;
    }

    private int calcularElo(int eloActual, int eloRival, boolean gano) {
        if (gano) {
            // Si ganó, aumenta el elo
            return eloActual + 10;
        } else {
            // Si perdió, disminuye el elo
            return eloActual - 10;
        }
    }
}
