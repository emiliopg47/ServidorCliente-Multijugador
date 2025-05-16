package com.emilio.servidor_multijugador.persistencia.servicios;

import com.emilio.servidor_multijugador.persistencia.modelos.Juego;
import com.emilio.servidor_multijugador.persistencia.repository.JuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ServiceJuego {

    @Autowired
    private JuegoRepository dao;

    public void create(Juego model) {
        dao.save(model);
    }
    public void update(Juego model) {
        dao.save(model);
    }
    public Juego read(Integer id) {
        return dao.findById(id).orElse(null);
    }
    public void delete(Integer id) {
        dao.deleteById(id);
    }

    public Juego findByNombre(String nombre) {
        return dao.findByNombre(nombre);
    }

}
