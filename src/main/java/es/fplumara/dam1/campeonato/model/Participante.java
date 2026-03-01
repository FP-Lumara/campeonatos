package es.fplumara.dam1.campeonato.model;

public abstract class Participante {
    private String id;
    private String nombre;
    private String pais;

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract String getPais();
}
