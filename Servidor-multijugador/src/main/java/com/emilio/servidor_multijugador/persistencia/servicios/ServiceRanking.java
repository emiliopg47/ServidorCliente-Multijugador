package com.emilio.servidor_multijugador.persistencia.servicios;

import com.emilio.servidor_multijugador.persistencia.modelos.Ranking;
import com.emilio.servidor_multijugador.persistencia.modelos.RankingId;
import com.emilio.servidor_multijugador.persistencia.repository.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceRanking {

    @Autowired
    private RankingRepository dao;


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
}
