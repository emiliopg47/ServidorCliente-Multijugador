package com.emilio.servidor_multijugador.persistencia.servicios;

import com.emilio.servidor_multijugador.persistencia.modelos.HistorialGameDTO;
import com.emilio.servidor_multijugador.persistencia.modelos.HistorialGames;
import com.emilio.servidor_multijugador.persistencia.modelos.Usuario;
import com.emilio.servidor_multijugador.persistencia.repository.HistorialGamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<HistorialGameDTO> buscarJuegos(Usuario usuario) {
        return dao.buscarJuegos(usuario);
    }
}
