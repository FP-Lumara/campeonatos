package es.fplumara.dam1.campeonato.repository;

import es.fplumara.dam1.campeonato.model.Deportista;

import java.util.List;
import java.util.Optional;

public interface DeportistaRepository {
    void save(Deportista d);
    Optional<Deportista> findById(String id);
    List<Deportista> findByPais (String pais);
    List<Deportista> listAll();
}
