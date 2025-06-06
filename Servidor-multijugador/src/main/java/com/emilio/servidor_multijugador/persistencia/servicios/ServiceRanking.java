package com.emilio.servidor_multijugador.persistencia.servicios;

import com.emilio.servidor_multijugador.persistencia.modelos.*;
import com.emilio.servidor_multijugador.persistencia.repository.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceRanking {

    @Autowired
    private RankingRepository dao;
    @Autowired
    private ServiceJuego serviceJuego;
    @Autowired
    private ServiceUsuario serviceUsuario;


    public void create(Ranking model) {
        dao.save(model);
    }

    public void update(Ranking model) {
        dao.save(model);
    }

    public Ranking read(RankingId id) {
        return dao.findById(id).orElse(null);
    }

    public void delete(RankingId id) {
        dao.deleteById(id);
    }

    public int getElo(String nick) {
        Juego juego = serviceJuego.findByNombre("Pong");
        Usuario usuario = serviceUsuario.findByNick(nick);
        Ranking ranking = dao.findByIdUsuarioAndIdJuego(usuario, juego);
        if (ranking != null) {
            return ranking.getPuntos();
        }
        return 100;
    }

    public void cambioElo(String player, int nuevoElo) {
        Juego juego = serviceJuego.findByNombre("Pong");
        Usuario usuario = serviceUsuario.findByNick(player);
        Ranking ranking = dao.findByIdUsuarioAndIdJuego(usuario, juego);
        if (ranking != null) {
            ranking.setPuntos(nuevoElo);
            dao.save(ranking);
        }
    }

    public List<TopRanking> getTopRanking(String juego) {
        Juego j = serviceJuego.findByNombre(juego);
        List<Ranking> rankings = dao.findTop5ByIdJuegoOrderByPuntosDesc(j);

        List<TopRanking> topRankings = rankings.stream()
                .map(r -> new TopRanking(
                        rankings.indexOf(r) + 1, // Posición en el ranking
                        r.getIdUsuario().getNick(),
                        r.getPuntos(),
                        r.getIdUsuario().getImagen()))
                .toList();
        return topRankings;
    }

}
