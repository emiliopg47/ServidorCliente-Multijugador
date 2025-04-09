package com.emilio.servidor_multijugador.persistencia.repository;

import com.emilio.servidor_multijugador.persistencia.modelos.Ranking;
import com.emilio.servidor_multijugador.persistencia.modelos.RankingId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankingRepository extends JpaRepository<Ranking, RankingId> {

}