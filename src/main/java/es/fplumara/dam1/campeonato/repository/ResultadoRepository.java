package es.fplumara.dam1.campeonato.repository;

import es.fplumara.dam1.campeonato.model.Resultado;

import java.util.List;
import java.util.Optional;

public interface ResultadoRepository {
    void save(Resultado r);
    Optional<Resultado> findById (String id);
    List<Resultado> listAll();
    boolean existsByPruebaYDeportista (String idPrueba, String idDeportista);
}
