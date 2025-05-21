package com.emilio.servidor_multijugador.web.apirest;

import com.emilio.servidor_multijugador.Util.Hash;
import com.emilio.servidor_multijugador.persistencia.modelos.Juego;
import com.emilio.servidor_multijugador.persistencia.modelos.Usuario;
import com.emilio.servidor_multijugador.persistencia.servicios.ServiceJuego;
import com.emilio.servidor_multijugador.persistencia.servicios.ServiceUsuario;
import com.emilio.servidor_multijugador.web.Mensajes.CambioFotoPerfilResponse;
import com.emilio.servidor_multijugador.web.apirest.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

public class RestControler{

    @Autowired
    private ServiceUsuario serviceUsuario;

    @Autowired
    private ServiceJuego serviceJuego;

    @PostMapping("/login")
    public ResponseEntity<DatosUsuarioResponse> login(@RequestBody Usuario usuario) {
        DatosUsuarioResponse loginResponse = comprobarLogin(usuario);
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
            // Encriptar la contraseña
            usuario.setPassword(Hash.hashPassword(usuario.getPassword()));
            serviceUsuario.create(usuario);
            return ResponseEntity.ok(registerResponse);
        } else {
            return ResponseEntity.badRequest().body(registerResponse);
        }
    }

    @GetMapping("/infoJuego/{juego}")
    public ResponseEntity<GameInfoResponse> info(@PathVariable String juego) {
        Juego j = serviceJuego.findByNombre(juego);
        GameInfoResponse gameInfoResponse = new GameInfoResponse(j);
        return ResponseEntity.ok(gameInfoResponse);
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
    @PostMapping("/cambiarFotoPerfil")
    public ResponseEntity<CambioFotoPerfilResponse> cambiarFotoPerfil(@RequestBody byte[] imagen, @RequestParam String nick, @RequestParam String password) {
        Usuario usuario = serviceUsuario.findByNickAndPassword(nick, password);
        if (usuario == null) {
            return ResponseEntity.badRequest().body(new CambioFotoPerfilResponse(false, Mensajes.USUARIO_NO_ENCONTRADO, null));
        }
        usuario.setImagen(imagen);
        try{
            serviceUsuario.update(usuario);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(new CambioFotoPerfilResponse(false, Mensajes.ERROR_AL_CAMBIAR_IMAGEN + " en cambiarFotoPerfil() " + this.getClass(), null));
        }
        CambioFotoPerfilResponse response = new CambioFotoPerfilResponse(true, Mensajes.CAMBIO_FOTO_PERFIL_EXITOSO, imagen);
        return ResponseEntity.ok(response);
    }

    private EloResponse comprobarRanking(String nick, String juego) {
        Long elo = serviceUsuario.findPuntosByNickAndJuego(nick, juego);
        if (elo == null) {
            return new EloResponse(false, Mensajes.RANKING_NO_ENCONTRADO, null);
        }
        return new EloResponse(true, "", elo);
    }

    private DatosUsuarioResponse comprobarLogin(Usuario usuario) {
        // Metodo para comprobar el login de un usuario
        if (usuario.getCorreo() == null) {
            if (!serviceUsuario.existByNick(usuario.getNick())) {
                return new DatosUsuarioResponse(false, Mensajes.USUARIO_NO_ENCONTRADO, null);
            }
            Usuario u = serviceUsuario.findByNick(usuario.getNick());
            if (!Hash.verificarPassword(usuario.getPassword(), u.getPassword())) {
                return new DatosUsuarioResponse(false, Mensajes.CONTRASENA_INCORRECTA, null);
            }
            return new DatosUsuarioResponse(true, Mensajes.CONEXION_EXITOSA, u);
        }
        if (usuario.getNick() == null) {
            if (!serviceUsuario.existByCorreo(usuario.getCorreo())) {
                return new DatosUsuarioResponse(false, Mensajes.USUARIO_NO_ENCONTRADO, null);
            }
            Usuario u = serviceUsuario.findByCorreo(usuario.getCorreo());
            if (!Hash.verificarPassword(usuario.getPassword(), u.getPassword())) {
                return new DatosUsuarioResponse(false, Mensajes.CONTRASENA_INCORRECTA, null);
            }
            return new DatosUsuarioResponse(true, Mensajes.CONEXION_EXITOSA, u);
        }
        return new DatosUsuarioResponse(false, Mensajes.ERROR_INESPERADO + " en comprobarLogin() " + this.getClass(), null);
    }

    private RegisterResponse comprobarRegistro(Usuario usuario) {
        // Metodo para comprobar el registro de un usuario
        if (usuario.getNick() == null && usuario.getCorreo() == null && usuario.getPassword() == null && usuario.getFechaNac() == null) {
            return new RegisterResponse(false, Mensajes.CAMPOS_INCOMPELTOS + "en comprobarRegistro()" + this.getClass());
        }
        if (usuario.getNick() != null && serviceUsuario.existByNick(usuario.getNick())) {
            return new RegisterResponse(false, Mensajes.NICK_YA_EXISTE);
        }
        if (usuario.getCorreo() != null && serviceUsuario.existByCorreo(usuario.getCorreo())) {
            return new RegisterResponse(false, Mensajes.CORREO_YA_EXISTE);
        }
        return new RegisterResponse(true, Mensajes.REGISTRO_EXITOSO);
    }
}
