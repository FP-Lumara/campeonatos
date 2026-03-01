package es.fplumara.dam1.campeonato.repository;

import es.fplumara.dam1.campeonato.model.Resultado;

import java.util.*;

public class ResultadoRepositoryImpl implements ResultadoRepository{
    Map<String, Resultado> datos = new HashMap<>();

    @Override
    public void save(Resultado r) {
        datos.put(r.getId(), r);
    }

    @Override
    public Optional<Resultado> findById(String id) {
        if(datos.containsKey(id)){
            return Optional.ofNullable(datos.get(id));
        }else {
            return Optional.empty();
        }
    }

    @Override
    public List<Resultado> listAll() {
        return new ArrayList<>(datos.values());
    }

    @Override
    public boolean existsByPruebaYDeportista(String idPrueba, String idDeportista) {
        return !listAll().stream().filter(r -> r.getIdDeportista().equalsIgnoreCase(idDeportista) && r.getIdPrueba().equalsIgnoreCase(idPrueba)).toList().isEmpty();
    }
}
