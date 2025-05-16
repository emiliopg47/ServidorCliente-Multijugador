package com.emilio.servidor_multijugador.persistencia.servicios;

import com.emilio.servidor_multijugador.persistencia.modelos.Usuario;
import com.emilio.servidor_multijugador.persistencia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceUsuario {

    @Autowired
    private UsuarioRepository dao;

    public void create(Usuario model) {
        dao.save(model);
    }
    public void update(Usuario model) {
        dao.save(model);
    }
    public Usuario read(Integer id) {
        return dao.findById(id).orElse(null);
    }
    public void deleteById(Integer id) {
        dao.deleteById(id);
    }
    public boolean existByNick(String nick) {
        return dao.existsByNick(nick);
    }

    public Usuario findByNick(String nick) {
        return dao.findByNick(nick);
    }

    public Usuario findByCorreo(String correo) {
        return dao.findByCorreo(correo);
    }

    public boolean existByCorreo(String correo) {
        return dao.existsByCorreo(correo);
    }

    public Usuario findByCorreoAndPassword(String correo, String password) {
        return dao.findByCorreoAndPassword(correo,password);
    }

    public Usuario findByNickAndPassword(String correo, String password) {
        return dao.findByNickAndPassword(correo,password);
    }

    public Long findPuntosByNickAndJuego(String nick, String juego) {
        return dao.findRankingByNickAndJuego(nick,juego);
    }
}
