package entities;

public class Diverse extends Produs {
    private Boolean compatibilCuChitara;
    private Boolean compatibilCuClaviatura;

    private Boolean contineSoftware;
    private Boolean contineHardware;    // pot exista produse care sunt si hardware si software

    public Diverse() {}

    public Diverse(Boolean compatibilCuChitara, Boolean compatibilCuClaviatura,
                   Boolean contineSoftware, Boolean contineHardware,
                   long id, double pret, int cantitate, String numeProducator, String numeModel) {
        super(id, pret, cantitate, numeProducator, numeModel);
        this.compatibilCuChitara = compatibilCuChitara;
        this.compatibilCuClaviatura = compatibilCuClaviatura;
        this.contineSoftware = contineSoftware;
        this.contineHardware = contineHardware;
    }

    public Boolean getCompatibilCuChitara() {
        return compatibilCuChitara;
    }

    public void setCompatibilCuChitara(Boolean compatibilCuChitara) {
        this.compatibilCuChitara = compatibilCuChitara;
    }

    public Boolean getCompatibilCuClaviatura() {
        return compatibilCuClaviatura;
    }

    public void setCompatibilCuClaviatura(Boolean compatibilCuClaviatura) {
        this.compatibilCuClaviatura = compatibilCuClaviatura;
    }

    public Boolean getContineSoftware() {
        return contineSoftware;
    }

    public void setContineSoftware(Boolean contineSoftware) {
        this.contineSoftware = contineSoftware;
    }

    public Boolean getContineHardware() {
        return contineHardware;
    }

    public void setContineHardware(Boolean contineHardware) {
        this.contineHardware = contineHardware;
    }
}
