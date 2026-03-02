package es.fplumara.dam1.campeonato.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Resultado implements Puntuable{
    private String id;
    private String idPrueba;
    private TipoPrueba tipoPrueba;
    private String idDeportista;
    private int posicion;

    @Override
    public int getPuntos() {
        return 0;
    }
}
