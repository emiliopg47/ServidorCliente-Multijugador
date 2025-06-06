package com.emilio.servidor_multijugador.persistencia.repository;

import com.emilio.servidor_multijugador.persistencia.modelos.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RankingRepository extends JpaRepository<Ranking, RankingId> {

    Ranking findByIdUsuarioAndIdJuego(Usuario idUsuario, Juego idJuego);

    List<Ranking> findTop5ByIdJuegoOrderByPuntosDesc(Juego idJuego);
}