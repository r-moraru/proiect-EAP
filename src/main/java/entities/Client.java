package entities;

public class Client {
    private Long id;
    private String nume;
    private String prenume;
    private String adresaMail;

    public Client(long id, String nume, String prenume, String adresaMail) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.adresaMail = adresaMail;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getAdresaMail() {
        return adresaMail;
    }

    public void setAdresaMail(String adresaMail) {
        this.adresaMail = adresaMail;
    }
}
