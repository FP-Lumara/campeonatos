package es.fplumara.dam1.campeonato.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Participante {
    private String id;
    private String nombre;
    private String pais;
}
