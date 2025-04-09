package com.emilio.servidor_multijugador.web.apirest;

import com.emilio.servidor_multijugador.persistencia.modelos.Usuario;
import com.emilio.servidor_multijugador.persistencia.repository.UsuarioRepository;
import com.emilio.servidor_multijugador.persistencia.servicios.ServiceUsuario;
import com.emilio.servidor_multijugador.web.apirest.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

@RestController
@RequestMapping("/api")

public class RestControler{

    @Autowired
    private ServiceUsuario serviceUsuario;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    private ResourceUrlProvider resourceUrlProvider;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody Usuario usuario) {
        return comprobarLogin(usuario);
    }

    @PostMapping("/register")
    public RegistrerResponse registre(@RequestBody Usuario usuario){
        // Metodo para registrar un usuario
        return null;
    }

    @GetMapping("/ranking/{nick,juego}")
    public RankingResponse ranking(@PathVariable String nick, @PathVariable String juego) {
        // Metodo para obtener el ranking de un juego
        return null;
    }

    @PostMapping("/matchmaking")
    public MatchMakingResponse matchmaking(@RequestBody Usuario usuario) {
        // Metodo para hacer matchmaking
        return null;
    }

    private LoginResponse comprobarLogin(Usuario usuario) {

        if (usuario.getCorreo() == null) {
            if (!serviceUsuario.findByNick(usuario.getNick())) {
                return new LoginResponse(false, Mensajes.USUARIO_NO_ENCONTRADO, null);
            }
            Usuario u = usuarioRepository.findByNickAndPassword(usuario.getNick(), usuario.getPassword());
            if (u == null) {
                return new LoginResponse(false, Mensajes.CONTRASENA_INCORRECTA, null);
            }
            return new LoginResponse(true, Mensajes.CONEXION_EXITOSA, u);
        }


        if (usuario.getNick() == null) {
            if (!serviceUsuario.findByCorreo(usuario.getCorreo())) {
                return new LoginResponse(false, Mensajes.USUARIO_NO_ENCONTRADO, null);
            }
            Usuario u = usuarioRepository.findByCorreoAndPassword(usuario.getCorreo(), usuario.getPassword());
            if (u == null) {
                return new LoginResponse(false, Mensajes.CONTRASENA_INCORRECTA, null);
            }
            return new LoginResponse(true, Mensajes.CONEXION_EXITOSA, u);
        }
        return new LoginResponse(false, Mensajes.ERROR_INESPERADO + "en comprobarLogin()" + this.getClass() , null);
    }
}
