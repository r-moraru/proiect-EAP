package entities;

public class Chitara extends Produs {
    private Integer numarCorzi;

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
    private Integer nrFreturi;

    private Boolean pentruStangaci;

    public Chitara() {}

    public Chitara(int numarCorzi, Tip tip, Lemn lemnFretboard, Lemn lemnBody,
                   int nrFreturi, boolean pentruStangaci,
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

    public boolean getPentruStangaci() {
        return pentruStangaci;
    }

    public void setPentruStangaci(boolean pentruStangaci) {
        this.pentruStangaci = pentruStangaci;
    }

    // TODO: toString specializat
}
