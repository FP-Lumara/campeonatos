package es.fplumara.dam1.campeonato.app;

/**
 * Main de ejemplo para demostrar el flujo mínimo del examen (sin menú complejo).
 * Debe leer ficheros de entrada y escribir un fichero de salida.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Examen DAM1 - Campeonato deportivo (Java 21)");

        /*
         * FLUJO MÍNIMO (lo que debe hacer tu main)
         *
         * 1) Crear repositorios en memoria
         *    - DeportistaRepositoryImpl
         *    - ResultadoRepositoryImpl
         *
         * 2) Crear el servicio
         *    - CampeonatoService (usa ambos repositorios)
         *
         * 3) Leer datos de ficheros (CSV recomendado)
         *    - Leer "deportistas.csv" y por cada línea crear Deportista y llamar a registrarDeportista(...)
         *    - Leer "resultados.csv" y por cada línea crear Resultado (incluyendo tipoPrueba como enum) y llamar a registrarResultado(...)
         *
         * 4) Mostrar por consola
         *    - Países participantes (Set)
         *    - Ranking (List ordenada por puntos)
         *    - Resultados de un país (List filtrada)
         *
         * 5) Escribir salida a fichero
         *    - Generar el ranking y escribir "ranking.csv" con: idDeportista,nombre,pais,puntos
         */
    }
}
