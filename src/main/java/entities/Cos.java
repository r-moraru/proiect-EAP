package entities;

import java.util.Map;
import java.util.TreeMap;

public class Cos {
    private Long idUser;
    private Map<Produs, Integer> produse = new TreeMap<>();

    public Cos(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Map<Produs, Integer> getProduse() {
        return new TreeMap<>(produse);
    }

    public void setCantitateProdus(Produs produs, int cantitate) {
        // TODO: throw out of bounds exception if cantitate < 0
        if (cantitate == 0) {
            if (produse.containsKey(produs))
                produse.remove(produs);
            // TODO: else throw exception
        }
        produse.put(produs, cantitate);
    }

    public void setProduse(Map<Produs, Integer> produse) {
        this.produse = produse;
    }
}
