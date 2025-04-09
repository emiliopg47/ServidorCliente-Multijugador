package com.emilio.servidor_multijugador.web.apirest;

import com.emilio.servidor_multijugador.persistencia.modelos.Usuario;
import com.emilio.servidor_multijugador.persistencia.repository.UsuarioRepository;
import com.emilio.servidor_multijugador.persistencia.servicios.ServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

public class RestControler{

    @Autowired
    private ServiceUsuario serviceUsuario;
    @Autowired
    UsuarioRepository usuarioRepository;

   @PostMapping("/login")
    public Usuario login(@RequestBody Usuario usuario) {
        return comprobarLogin(usuario);
    }



    @GetMapping("/pruebaget")
    public String pruebaGet() {
        return "Hola";
    }

    private Usuario comprobarLogin(Usuario usuario) {

        if(usuario.getCorreo() == null){
            // Si el correo esta vacio, el user quiere logearse con el nick
            return serviceUsuario.findByNick(usuario.getNick(), usuario.getPassword());
        }
        if (usuario.getNick() == null){
            // Si el nick esta vacio, el user quiere logearse con el correo
            return serviceUsuario.findByNick(usuario.getCorreo(), usuario.getPassword());
        }
        return null;
    }
}
