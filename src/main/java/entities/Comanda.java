package entities;

import java.util.Date;
import java.util.Map;

public class Comanda {
    private Long id;
    private Long idUser;
    private String adresaLivrare;
    private Map<Produs, Integer> produse;
    private Date dataPlasarii;

    public Comanda(Long id, Long idUser, String adresaLivrare, Map<Produs, Integer> produse) {
        this.id = id;
        this.idUser = idUser;
        this.adresaLivrare = adresaLivrare;
        this.produse = produse;
        this.dataPlasarii = new Date();         // data la care a fost creat obiectul
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getAdresaLivrare() {
        return adresaLivrare;
    }

    public void setAdresaLivrare(String adresaLivrare) {
        this.adresaLivrare = adresaLivrare;
    }

    public Map<Produs, Integer> getProduse() {
        return produse;
    }

    public void setProduse(Map<Produs, Integer> produse) {
        this.produse = produse;
    }

    public Date getDataPlasarii() {
        return dataPlasarii;
    }

    public void setDataPlasarii(Date dataPlasarii) {
        this.dataPlasarii = dataPlasarii;
    }

    @Override
    public String toString() {
        return id + " " + idUser + " livrat la " + adresaLivrare + "\n" + produse + "\n" +
                dataPlasarii + "\n";
    }
}
