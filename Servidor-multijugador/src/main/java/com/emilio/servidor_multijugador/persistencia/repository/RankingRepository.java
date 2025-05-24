package com.emilio.servidor_multijugador.persistencia.repository;

import com.emilio.servidor_multijugador.persistencia.modelos.Juego;
import com.emilio.servidor_multijugador.persistencia.modelos.Ranking;
import com.emilio.servidor_multijugador.persistencia.modelos.RankingId;
import com.emilio.servidor_multijugador.persistencia.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RankingRepository extends JpaRepository<Ranking, RankingId> {

    Ranking findByIdUsuarioAndIdJuego(Usuario idUsuario, Juego idJuego);
}