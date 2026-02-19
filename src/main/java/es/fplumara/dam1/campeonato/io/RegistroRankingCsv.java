package es.fplumara.dam1.campeonato.io;

/**
 * DTO de salida para CSV del ranking.
 * Los alumnos deben construir esta salida a partir de su ranking de dominio.
 */
public record RegistroRankingCsv(String idDeportista, String nombre, String pais, int puntos) {

    public RegistroRankingCsv {
        idDeportista = obligatorio(idDeportista, "idDeportista");
        nombre = obligatorio(nombre, "nombre");
        pais = obligatorio(pais, "pais");
        if (puntos < 0) {
            throw new IllegalArgumentException("puntos no puede ser negativo");
        }
    }

    private static String obligatorio(String v, String campo) {
        if (v == null || v.trim().isEmpty()) {
            throw new IllegalArgumentException("Campo obligatorio vacío: " + campo);
        }
        return v.trim();
    }
}
