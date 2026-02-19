package es.fplumara.dam1.campeonato.io;

/**
 * DTO de entrada/salida para CSV de resultados.
 * Los alumnos deben convertir este registro a su modelo Resultado.
 *
 * tipoPrueba debe coincidir con el enum del dominio (por ejemplo: CARRERA, SALTO, LANZAMIENTO).
 */
public record RegistroResultadoCsv(
        String id,
        String idPrueba,
        String tipoPrueba,
        String idDeportista,
        int posicion
) {
    public RegistroResultadoCsv {
        id = obligatorio(id, "id");
        idPrueba = obligatorio(idPrueba, "idPrueba");
        tipoPrueba = obligatorio(tipoPrueba, "tipoPrueba");
        idDeportista = obligatorio(idDeportista, "idDeportista");
        if (posicion <= 0) {
            throw new IllegalArgumentException("posicion debe ser > 0");
        }
    }

    private static String obligatorio(String v, String campo) {
        if (v == null || v.trim().isEmpty()) {
            throw new IllegalArgumentException("Campo obligatorio vacío: " + campo);
        }
        return v.trim();
    }
}
