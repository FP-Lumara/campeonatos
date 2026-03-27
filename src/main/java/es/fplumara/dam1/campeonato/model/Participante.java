package es.fplumara.dam1.campeonato.model;

public abstract class Participante {
    private String id;
    private String nombre;
    private String pais;

    public Participante(String nombre, String id, String pais) {
        this.nombre = nombre;
        this.id = id;
        this.pais = pais;
    }

    public Participante() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
