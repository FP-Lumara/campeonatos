package es.fplumara.dam1.campeonato.model;

public class Resultado implements Puntuable {

    private String id;
    private String idPrueba;
    private TipoPrueba tipoPrueba;
    private String idDeportista;
    private int posicion;

    @Override
    public int getPuntos() {
        if(posicion == 1){
            return 5;
        } else if (posicion == 2) {
            return 3;
        } else if (posicion == 3) {
            return 1;
        }else{
            return 0;
        }
    }

    public String getId() {
        return id;
    }

    public String getIdPrueba() {
        return idPrueba;
    }

    public TipoPrueba getTipoPrueba() {
        return tipoPrueba;
    }

    public String getIdDeportista() {
        return idDeportista;
    }

    public int getPosicion() {
        return posicion;
    }
}
