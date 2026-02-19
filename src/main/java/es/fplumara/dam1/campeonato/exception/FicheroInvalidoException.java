package es.fplumara.dam1.campeonato.exception;

/**
 * Excepción para indicar que un fichero de entrada/salida no cumple el formato esperado
 * o no puede procesarse correctamente.
 */
public class FicheroInvalidoException extends RuntimeException {

    public FicheroInvalidoException(String message) {
        super(message);
    }

    public FicheroInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }
}
