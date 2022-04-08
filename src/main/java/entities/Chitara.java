package entities;

public class Chitara extends Produs {
    private int numarCorzi;

    public enum Tip {
        BASS_ELECTRIC,
        BASS_ACUSTIC,
        ELECTRICA,
        ACUSTICA,
        CLASICA
    }

    private Tip tip;

    public enum Lemn {
        ROSEWOOD,
        MAHOGANY,
        ALDER,
        BASSWOOD
    }

    private Lemn lemnFretboard;
    private Lemn lemnBody;
    private int nrFreturi;

    private Boolean pentruStangaci;

    public Chitara(int numarCorzi, Tip tip, Lemn lemnFretboard, Lemn lemnBody,
                   int nrFreturi, Boolean pentruStangaci,
                   long id, double pret, int cantitate, String numeProducator, String numeModel) {
        super(id, pret, cantitate, numeProducator, numeModel);
        this.numarCorzi = numarCorzi;
        this.tip = tip;
        this.lemnFretboard = lemnFretboard;
        this.lemnBody = lemnBody;
        this.nrFreturi = nrFreturi;
        this.pentruStangaci = pentruStangaci;
    }

    public int getNumarCorzi() {
        return numarCorzi;
    }

    public void setNumarCorzi(int numarCorzi) {
        this.numarCorzi = numarCorzi;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public Lemn getLemnFretboard() {
        return lemnFretboard;
    }

    public void setLemnFretboard(Lemn lemnFretboard) {
        this.lemnFretboard = lemnFretboard;
    }

    public Lemn getLemnBody() {
        return lemnBody;
    }

    public void setLemnBody(Lemn lemnBody) {
        this.lemnBody = lemnBody;
    }

    public int getNrFreturi() {
        return nrFreturi;
    }

    public void setNrFreturi(int nrFreturi) {
        this.nrFreturi = nrFreturi;
    }

    public Boolean getPentruStangaci() {
        return pentruStangaci;
    }

    public void setPentruStangaci(Boolean pentruStangaci) {
        this.pentruStangaci = pentruStangaci;
    }

    // TODO: toString specializat
}
