package es.fplumara.dam1.campeonato.model;

public class Deportista extends Participante{
   private String pais;

    public Deportista(String nombre, String id, String pais, String pais1) {
        super(nombre, id, pais);
        this.pais = pais1;
    }

    public Deportista(String pais) {
        this.pais = pais;
    }

    @Override
    public String getPais() {
        return pais;
    }

    @Override
    public void setPais(String pais) {
        this.pais = pais;
    }
}
