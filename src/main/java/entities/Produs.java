package entities;

public class Produs implements Comparable<Produs> {
    public enum OrderCrit {
        NUME,
        PRET
    }

    public enum OrderType {
        ASCENDING,
        DESCENDING
    }

     private Long id;
     private Double pret;
     private int cantitate;
     private String numeProducator;
     private String numeModel;

    public Produs(Long id, double pret, int cantitate,
                  String numeProducator, String numeModel) {
        this.id = id;
        this.pret = pret;
        this.cantitate = cantitate;
        this.numeProducator = numeProducator;
        this.numeModel = numeModel;
    }

    @Override
    public int compareTo(Produs other) {
        return this.id.compareTo(other.id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public String getNumeProducator() {
        return numeProducator;
    }

    public void setNumeProducator(String numeProducator) {
        this.numeProducator = numeProducator;
    }

    public String getNumeModel() {
        return numeModel;
    }

    public void setNumeModel(String numeModel) {
        this.numeModel = numeModel;
    }

    @Override
    public String toString() {
        return numeProducator + " " + numeModel + " pret: " + pret + " cantitate: " + cantitate;
    }
}

