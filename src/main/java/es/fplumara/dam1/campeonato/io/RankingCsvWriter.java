package es.fplumara.dam1.campeonato.io;

import es.fplumara.dam1.campeonato.exception.FicheroInvalidoException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Escribe el ranking a un fichero CSV.
 *
 * Salida: ranking.csv
 *   idDeportista,nombre,pais,puntos
 *
 * IMPORTANTE:
 * - Recibe List<RegistroRankingCsv> (DTO).
 * - Los alumnos deben construir esta salida a partir de su ranking de dominio.
 */
public class RankingCsvWriter {

    public void escribir(Path fichero, List<RegistroRankingCsv> ranking) {
        if (fichero == null) throw new IllegalArgumentException("fichero no puede ser null");
        if (ranking == null) throw new IllegalArgumentException("ranking no puede ser null");

        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader("idDeportista", "nombre", "pais", "puntos")
                .setTrim(true)
                .build();

        try (BufferedWriter bw = Files.newBufferedWriter(fichero, StandardCharsets.UTF_8);
             CSVPrinter printer = format.print(bw)) {

            for (RegistroRankingCsv l : ranking) {
                printer.printRecord(
                        l.idDeportista(),
                        l.nombre(),
                        l.pais(),
                        l.puntos()
                );
            }

        } catch (IOException e) {
            throw new FicheroInvalidoException("Error escribiendo ranking.csv: " + e.getMessage(), e);
        }
    }
}
