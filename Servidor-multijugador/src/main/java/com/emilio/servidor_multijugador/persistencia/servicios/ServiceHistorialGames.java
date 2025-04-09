package com.emilio.servidor_multijugador.persistencia.servicios;

import com.emilio.servidor_multijugador.persistencia.modelos.HistorialGames;
import com.emilio.servidor_multijugador.persistencia.repository.HistorialGamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceHistorialGames {

    @Autowired
    private HistorialGamesRepository dao;


    public void create(HistorialGames model) {
        dao.save(model);
    }

    public void update(HistorialGames model) {
        dao.save(model);
    }

    public HistorialGames read(Integer id) {
        return dao.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        dao.deleteById(id);
    }
}
