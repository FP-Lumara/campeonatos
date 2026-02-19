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
 * Utilidad para leer/escribir el CSV de deportistas.
 *
 * Entrada: deportistas.csv
 *   id,nombre,pais
 *   D001,Ana,ES
 *
 * IMPORTANTE:
 * - Devuelve List<RegistroDeportistaCsv> (DTO).
 * - Los alumnos deben transformar estos registros a su clase Deportista.
 */
public class DeportistaCsvIO {

    private static final String COL_ID = "id";
    private static final String COL_NOMBRE = "nombre";
    private static final String COL_PAIS = "pais";

    public List<RegistroDeportistaCsv> leer(Path fichero) {
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

            validarCabecera(parser.getHeaderMap(), COL_ID, COL_NOMBRE, COL_PAIS);

            List<RegistroDeportistaCsv> out = new ArrayList<>();
            for (CSVRecord r : parser) {
                String id = obligatorio(r, COL_ID);
                String nombre = obligatorio(r, COL_NOMBRE);
                String pais = obligatorio(r, COL_PAIS);

                out.add(new RegistroDeportistaCsv(id, nombre, pais));
            }
            return out;

        } catch (IOException e) {
            throw new FicheroInvalidoException("Error leyendo deportistas.csv: " + e.getMessage(), e);
        }
    }

    public void escribir(Path fichero, List<RegistroDeportistaCsv> deportistas) {
        if (fichero == null) throw new IllegalArgumentException("fichero no puede ser null");
        if (deportistas == null) throw new IllegalArgumentException("deportistas no puede ser null");

        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader(COL_ID, COL_NOMBRE, COL_PAIS)
                .setTrim(true)
                .build();

        try (BufferedWriter bw = Files.newBufferedWriter(fichero, StandardCharsets.UTF_8);
             CSVPrinter printer = format.print(bw)) {

            for (RegistroDeportistaCsv d : deportistas) {
                printer.printRecord(d.id(), d.nombre(), d.pais());
            }

        } catch (IOException e) {
            throw new FicheroInvalidoException("Error escribiendo deportistas.csv: " + e.getMessage(), e);
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
}
