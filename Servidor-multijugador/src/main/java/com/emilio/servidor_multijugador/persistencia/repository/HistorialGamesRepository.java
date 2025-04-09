package com.emilio.servidor_multijugador.persistencia.repository;

import com.emilio.servidor_multijugador.persistencia.modelos.HistorialGames;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistorialGamesRepository extends JpaRepository<HistorialGames, Integer> {

}
