package es.fplumara.dam1.campeonato.io;

import es.fplumara.dam1.campeonato.exception.FicheroInvalidoException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Utilidad para leer/escribir el CSV de resultados.
 *
 * Entrada: resultados.csv
 *   id,idPrueba,tipoPrueba,idDeportista,posicion
 *   R001,P001,CARRERA,D001,1
 *
 * IMPORTANTE:
 * - Devuelve List<RegistroResultadoCsv> (DTO).
 * - Los alumnos deben transformar estos registros a su clase Resultado y el enum TipoPrueba del dominio.
 */
public class ResultadoCsvIO {

    private static final String COL_ID = "id";
    private static final String COL_ID_PRUEBA = "idPrueba";
    private static final String COL_TIPO_PRUEBA = "tipoPrueba";
    private static final String COL_ID_DEPORTISTA = "idDeportista";
    private static final String COL_POSICION = "posicion";

    public List<RegistroResultadoCsv> leer(Path fichero) {
        if (fichero == null) throw new IllegalArgumentException("fichero no puede ser null");
        if (!Files.exists(fichero)) throw new FicheroInvalidoException("No existe el fichero: " + fichero);

        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setTrim(true)
                .setIgnoreEmptyLines(true)
                .build();

        try (BufferedReader br = Files.newBufferedReader(fichero, StandardCharsets.UTF_8);
             CSVParser parser = format.parse(br)) {

            validarCabecera(parser.getHeaderMap(), COL_ID, COL_ID_PRUEBA, COL_TIPO_PRUEBA, COL_ID_DEPORTISTA, COL_POSICION);

            List<RegistroResultadoCsv> out = new ArrayList<>();
            for (CSVRecord r : parser) {
                String id = obligatorio(r, COL_ID);
                String idPrueba = obligatorio(r, COL_ID_PRUEBA);
                String tipoPrueba = obligatorio(r, COL_TIPO_PRUEBA);
                String idDeportista = obligatorio(r, COL_ID_DEPORTISTA);

                int posicion = parseIntObligatorio(r, COL_POSICION);

                out.add(new RegistroResultadoCsv(id, idPrueba, tipoPrueba, idDeportista, posicion));
            }
            return out;

        } catch (IOException e) {
            throw new FicheroInvalidoException("Error leyendo resultados.csv: " + e.getMessage(), e);
        }
    }

    public void escribir(Path fichero, List<RegistroResultadoCsv> resultados) {
        if (fichero == null) throw new IllegalArgumentException("fichero no puede ser null");
        if (resultados == null) throw new IllegalArgumentException("resultados no puede ser null");

        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader(COL_ID, COL_ID_PRUEBA, COL_TIPO_PRUEBA, COL_ID_DEPORTISTA, COL_POSICION)
                .setTrim(true)
                .build();

        try (BufferedWriter bw = Files.newBufferedWriter(fichero, StandardCharsets.UTF_8);
             CSVPrinter printer = format.print(bw)) {

            for (RegistroResultadoCsv r : resultados) {
                printer.printRecord(
                        r.id(),
                        r.idPrueba(),
                        r.tipoPrueba(),
                        r.idDeportista(),
                        r.posicion()
                );
            }

        } catch (IOException e) {
            throw new FicheroInvalidoException("Error escribiendo resultados.csv: " + e.getMessage(), e);
        }
    }

    private void validarCabecera(Map<String, Integer> headerMap, String... obligatorias) {
        if (headerMap == null || headerMap.isEmpty()) {
            throw new FicheroInvalidoException("El CSV no tiene cabecera.");
        }
        for (String col : obligatorias) {
            if (!headerMap.containsKey(col)) {
                throw new FicheroInvalidoException("Falta columna obligatoria en cabecera: " + col);
            }
        }
    }

    private String obligatorio(CSVRecord r, String col) {
        String v = r.get(col);
        if (v == null || v.trim().isEmpty()) {
            throw new FicheroInvalidoException("Campo obligatorio vacío: " + col + " (línea " + r.getRecordNumber() + ")");
        }
        return v.trim();
    }

    private int parseIntObligatorio(CSVRecord r, String col) {
        String v = obligatorio(r, col);
        try {
            return Integer.parseInt(v);
        } catch (NumberFormatException ex) {
            throw new FicheroInvalidoException("Número inválido en '" + col + "': " + v + " (línea " + r.getRecordNumber() + ")");
        }
    }
}
