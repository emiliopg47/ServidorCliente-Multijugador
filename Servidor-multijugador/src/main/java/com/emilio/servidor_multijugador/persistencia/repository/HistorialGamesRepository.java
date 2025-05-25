package com.emilio.servidor_multijugador.persistencia.repository;

import com.emilio.servidor_multijugador.persistencia.modelos.HistorialGameDTO;
import com.emilio.servidor_multijugador.persistencia.modelos.HistorialGames;
import com.emilio.servidor_multijugador.persistencia.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HistorialGamesRepository extends JpaRepository<HistorialGames, Integer> {
    @Query("SELECT h.idJugador1.nick, h.idJugador2.nick, h.duracionSeg, h.fecha, h.puntosJ1, h.puntosJ2, h.winner FROM HistorialGames h WHERE h.idJugador1 = ?1 OR h.idJugador2 = ?1")
    List<HistorialGameDTO> buscarJuegos(Usuario idJugador1);
}
