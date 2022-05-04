package entities;

public class Claviatura extends Produs {
    private Integer numarClape;

    public enum Tip {
        MIDI_CONTROLLER,
        SINTETIZATOR,
        PIAN
    }

    private Tip tip;

    public Claviatura() {}

    public Claviatura(int numarClape, Tip tip,
                      long id, double pret, int cantitate,
                      String numeProducator, String numeModel) {
        super(id, pret, cantitate, numeProducator, numeModel);
        this.numarClape = numarClape;
        this.tip = tip;
    }

    public int getNumarClape() {
        return numarClape;
    }

    public void setNumarClape(int numarClape) {
        this.numarClape = numarClape;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }
}
