package es.fplumara.dam1.campeonato.io;

import java.util.Objects;

/**
 * DTO de entrada/salida para CSV de deportistas.
 * Los alumnos deben convertir este registro a su modelo Deportista.
 */
public record RegistroDeportistaCsv(String id, String nombre, String pais) {

    public RegistroDeportistaCsv {
        id = obligatorio(id, "id");
        nombre = obligatorio(nombre, "nombre");
        pais = obligatorio(pais, "pais");
    }

    private static String obligatorio(String v, String campo) {
        if (v == null || v.trim().isEmpty()) {
            throw new IllegalArgumentException("Campo obligatorio vacío: " + campo);
        }
        return v.trim();
    }
}
