package com.emilio.servidor_multijugador.web.apirest;

import com.emilio.servidor_multijugador.persistencia.modelos.Usuario;
import com.emilio.servidor_multijugador.persistencia.repository.UsuarioRepository;
import com.emilio.servidor_multijugador.persistencia.servicios.ServiceUsuario;
import com.emilio.servidor_multijugador.web.apirest.response.LoginResponse;
import com.emilio.servidor_multijugador.web.apirest.response.Mensajes;
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
    public LoginResponse login(@RequestBody Usuario usuario) {
        return comprobarLogin(usuario);
    }
    @GetMapping("/pruebaget")
    public String pruebaGet() {
        return "Hola";
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
