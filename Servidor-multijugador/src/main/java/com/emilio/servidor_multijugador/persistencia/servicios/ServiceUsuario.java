package com.emilio.servidor_multijugador.persistencia.servicios;

import com.emilio.servidor_multijugador.persistencia.repository.UsuarioRepository;
import com.emilio.servidor_multijugador.persistencia.modelos.Usuario;
import jakarta.annotation.PostConstruct;
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
    public Usuario findByNick(String nick, String password) {
        return dao.findByNickAndPassword(nick,password);
    }

    public Usuario findByCorreo(String correo, String password) {
        return dao.findByCorreoAndPassword(correo,password);
    }

}
