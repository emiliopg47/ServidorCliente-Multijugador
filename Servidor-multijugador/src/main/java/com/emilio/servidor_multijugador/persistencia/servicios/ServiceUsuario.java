package com.emilio.servidor_multijugador.persistencia.servicios;

import com.emilio.servidor_multijugador.persistencia.repository.UsuarioRepository;
import com.emilio.servidor_multijugador.persistencia.modelos.Usuario;
import com.emilio.servidor_multijugador.web.apirest.response.LoginResponse;
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
    public boolean findByNick(String nick) {
        return dao.findByNick(nick) ;
    }

    public boolean findByCorreo(String correo) {
        return dao.findByCorreo(correo);
    }

    public Usuario findByCorreoAndPassword(String correo, String password) {
        return dao.findByCorreoAndPassword(correo,password);
    }

    public Usuario findByNickAndPassword(String correo, String password) {
        return dao.findByCorreoAndPassword(correo,password);
    }

}
