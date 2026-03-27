package es.fplumara.dam1.campeonato.model;

public class LineaRanking {
    private String idDerportista;
    private String nombre;
    private String pais;
    private int puntos;

    public LineaRanking(String idDerportista, String nombre, String pais, int puntos) {
        this.idDerportista = idDerportista;
        this.nombre = nombre;
        this.pais = pais;
        this.puntos = puntos;
    }

    public LineaRanking() {
    }

    public String getIdDerportista() {
        return idDerportista;
    }

    public void setIdDerportista(String idDerportista) {
        this.idDerportista = idDerportista;
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

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
