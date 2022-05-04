package entities;

import java.util.Date;

public class Review {
    private Long idClient;
    private Long idProdus;
    private Integer scor;           // intre 0 si 10
    private String titlu;
    private String descriere;
    private Date data;

    public Review() {}

    public Review(Long idClient, Long idProdus, int scor, String titlu, String descriere) {
        this.idClient = idClient;
        this.idProdus = idProdus;
        this.scor = scor;
        this.titlu = titlu;
        this.descriere = descriere;
        this.data = new Date();
    }

    public void setScor(int scor) {
        this.scor = scor;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public Long getIdClient() {
        return idClient;
    }

    public Long getIdProdus() {
        return idProdus;
    }

    public int getScor() {
        return scor;
    }

    public String getTitlu() {
        return titlu;
    }

    public String getDescriere() {
        return descriere;
    }

    public Date getData() { return data; }

    @Override
    public String toString() {
        return "Review{" +
                "idClient=" + idClient +
                ", idProdus=" + idProdus +
                ", scor=" + scor +
                ", titlu='" + titlu + '\'' +
                ", descriere='" + descriere + '\'' +
                ", data=" + data +
                '}';
    }
}
