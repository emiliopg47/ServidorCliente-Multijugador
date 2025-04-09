package com.emilio.servidor_multijugador.persistencia.repository;

import com.emilio.servidor_multijugador.persistencia.modelos.Juego;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JuegoRepository extends JpaRepository<Juego, Integer> {

}
