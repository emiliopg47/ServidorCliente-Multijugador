package com.emilio.servidor_multijugador.persistencia.repository;
import com.emilio.servidor_multijugador.persistencia.modelos.Ranking;
import com.emilio.servidor_multijugador.persistencia.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByNickAndPassword(String nick, String password);
    Usuario findByCorreoAndPassword(String correo, String password);

    boolean existsByNick(String nick);

    boolean existsByCorreo(String correo);

    @Query("""
        select r.puntos
        from
            Ranking r
        where
            r.idUsuario.nick = ?1 and r.idJuego.nombre = ?2
    """)
    Long findRankingByNickAndJuego(String nick, String juego);
}
