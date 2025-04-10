package com.emilio.servidor_multijugador.web.apirest;

import com.emilio.servidor_multijugador.persistencia.modelos.Usuario;
import com.emilio.servidor_multijugador.persistencia.servicios.ServiceUsuario;
import com.emilio.servidor_multijugador.web.apirest.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

public class RestControler{

    @Autowired
    private ServiceUsuario serviceUsuario;

    @PostMapping("/pruebas")
    public ResponseEntity<LoginResponse> pruebas() {
        LoginResponse res = new LoginResponse(true, "Resultado: " + serviceUsuario.findByNick("emilio"), null);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody Usuario usuario) {
        LoginResponse loginResponse = comprobarLogin(usuario);
        if (loginResponse.isSuccess()) {
            return ResponseEntity.ok(loginResponse);
        } else {
            return ResponseEntity.badRequest().body(loginResponse);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> singIn(@RequestBody Usuario usuario){
        RegisterResponse registerResponse = comprobarRegistro(usuario);
        if (registerResponse.isSuccess()) {
            serviceUsuario.create(usuario);
            return ResponseEntity.ok(registerResponse);
        } else {
            return ResponseEntity.badRequest().body(registerResponse);
        }
    }

    @GetMapping("/elo/{nick}/{juego}")
    public ResponseEntity<EloResponse> elo(@PathVariable String nick, @PathVariable String juego) {
        EloResponse rankingResponse = comprobarRanking(nick,juego);
        if (rankingResponse.isSuccess()) {
            return ResponseEntity.ok(rankingResponse);
        } else {
            return ResponseEntity.badRequest().body(rankingResponse);
        }
    }

    @PostMapping("/matchmaking")
    public MatchMakingResponse matchmaking(@RequestBody Usuario usuario) {
        // Metodo para hacer matchmaking
        return null;
    }

    private EloResponse comprobarRanking(String nick, String juego) {
        Long elo = serviceUsuario.findPuntosByNickAndJuego(nick, juego);
        if (elo == null) {
            return new EloResponse(false, Mensajes.RANKING_NO_ENCONTRADO, null);
        }
        return new EloResponse(true, "", elo);
    }

    private LoginResponse comprobarLogin(Usuario usuario) {
        // Metodo para comprobar el login de un usuario
        if (usuario.getCorreo() == null) {
            if (!serviceUsuario.findByNick(usuario.getNick())) {
                return new LoginResponse(false, Mensajes.USUARIO_NO_ENCONTRADO, null);
            }
            Usuario u = serviceUsuario.findByNickAndPassword(usuario.getNick(), usuario.getPassword());
            if (u == null) {
                return new LoginResponse(false, Mensajes.CONTRASENA_INCORRECTA, null);
            }
            return new LoginResponse(true, Mensajes.CONEXION_EXITOSA, u);
        }
        if (usuario.getNick() == null) {
            if (!serviceUsuario.findByCorreo(usuario.getCorreo())) {
                return new LoginResponse(false, Mensajes.USUARIO_NO_ENCONTRADO, null);
            }
            Usuario u = serviceUsuario.findByCorreoAndPassword(usuario.getCorreo(), usuario.getPassword());
            if (u == null) {
                return new LoginResponse(false, Mensajes.CONTRASENA_INCORRECTA, null);
            }
            return new LoginResponse(true, Mensajes.CONEXION_EXITOSA, u);
        }
        return new LoginResponse(false, Mensajes.ERROR_INESPERADO + " en comprobarLogin() " + this.getClass(), null);
    }

    private RegisterResponse comprobarRegistro(Usuario usuario) {
        // Metodo para comprobar el registro de un usuario
        if (usuario.getNick() == null && usuario.getCorreo() == null && usuario.getPassword() == null && usuario.getFechaNac() == null) {
            return new RegisterResponse(false, Mensajes.CAMPOS_INCOMPELTOS + "en comprobarRegistro()" + this.getClass());
        }
        if (usuario.getNick() != null && serviceUsuario.findByNick(usuario.getNick())) {
            return new RegisterResponse(false, Mensajes.NICK_YA_EXISTE);
        }
        if (usuario.getCorreo() != null && serviceUsuario.findByCorreo(usuario.getCorreo())) {
            return new RegisterResponse(false, Mensajes.CORREO_YA_EXISTE);
        }
        return new RegisterResponse(true, Mensajes.REGISTRO_EXITOSO);
    }
}
