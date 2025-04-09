package com.emilio.servidor_multijugador.persistencia.repository;
import com.emilio.servidor_multijugador.persistencia.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByNickAndPassword(String nick, String password);
    Usuario findByCorreoAndPassword(String correo, String password);

    boolean findByNick(String nick);

    boolean findByCorreo(String correo);
}
