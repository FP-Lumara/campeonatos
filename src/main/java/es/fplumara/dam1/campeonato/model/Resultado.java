package es.fplumara.dam1.campeonato.model;

public class Resultado implements Puntuable{
    private String id;
    private String idPrueba;
    private TipoPrueba tipoPrueba;
    private String idDerportista;
    private int posicion;

    public Resultado(String id, String idPrueba, TipoPrueba tipoPrueba, String idDerportista, int posicion) {
        this.id = id;
        this.idPrueba = idPrueba;
        this.tipoPrueba = tipoPrueba;
        this.idDerportista = idDerportista;
        this.posicion = posicion;
    }

    public Resultado() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPrueba() {
        return idPrueba;
    }

    public void setIdPrueba(String idPrueba) {
        this.idPrueba = idPrueba;
    }

    public TipoPrueba getTipoPrueba() {
        return tipoPrueba;
    }

    public void setTipoPrueba(TipoPrueba tipoPrueba) {
        this.tipoPrueba = tipoPrueba;
    }

    public String getIdDerportista() {
        return idDerportista;
    }

    public void setIdDerportista(String idDerportista) {
        this.idDerportista = idDerportista;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    @Override
    public int getPuntos() {
        return 0;
    }
}
