package services;

import daos.ProdusDAO;
import entities.Chitara;
import entities.Claviatura;
import entities.Diverse;
import csv.services.CsvWriter;
import entities.Produs;

import java.util.Objects;

public class AdminService {
    private ProdusDAO produsDAO;
    private CsvWriter csvWriter;

    public AdminService(ProdusDAO produsDAO, CsvWriter csvWriter) {
        this.produsDAO = produsDAO;
        this.csvWriter = csvWriter;
    }

    // adauga produs
    public void addChitara(int numarCorzi, Chitara.Tip tip, Chitara.Lemn lemnFretboard,
                           Chitara.Lemn lemnBody, int nrFreturi, Boolean pentruStangaci,
                           long id, double pret, int cantitate, String numeProducator,
                           String numeModel) {

        Chitara chitara = new Chitara(numarCorzi, tip, lemnFretboard, lemnBody, nrFreturi,
                pentruStangaci, id, pret, cantitate, numeProducator, numeModel);

        produsDAO.saveProdus(chitara);
        try {
            csvWriter.saveData(Chitara.class, chitara, "src/main/resources/csv/chitari.csv");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addClaviatura(int numarClape, Claviatura.Tip tip,
                              long id, double pret, int cantitate,
                              String numeProducator, String numeModel) {
        Claviatura claviatura = new Claviatura(numarClape, tip, id, pret,
                cantitate, numeProducator, numeModel);

        produsDAO.saveProdus(claviatura);
        try {
            csvWriter.saveData(Claviatura.class, claviatura, "src/main/resources/csv/claviaturi.csv");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addDiverse(Boolean compatibilCuChitara, Boolean compatibilCuClaviatura,
                           Boolean contineSoftware, Boolean contineHardware,
                           long id, double pret, int cantitate,
                           String numeProducator, String numeModel) {
        Diverse diverse = new Diverse(compatibilCuChitara, compatibilCuClaviatura,
                contineSoftware, contineHardware, id, pret, cantitate, numeProducator,
                numeModel);

        produsDAO.saveProdus(diverse);
        try {
            csvWriter.saveData(Diverse.class, diverse, "src/main/resources/csv/diverse.csv");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // editeaza stoc produs
    public void adaugaCantitateChitara(Long prodId, Integer cantitate) {
        produsDAO.scadeCantitateChitara(Math.toIntExact(prodId), -cantitate);
    }

    public void adaugaCantitateClaviatura(Long prodId, Integer cantitate) {
        // produsDAO.scadeCantitateClaviatura(Math.toIntExact(prodId), -cantitate);
    }

    public void adaugaCantitateDiverse(Long prodId, Integer cantitate) {
        // produsDAO.scadeCantitateDiverse(Math.toIntExact(prodId), -cantitate);
    }

    // sterge produs
    public void removeProdus(String tip, int id) {
        Produs prod;

        if (Objects.equals(tip, "chitara"))
            prod = new Chitara();
        else if (Objects.equals(tip, "claviatura"))
            prod = new Claviatura();
        else prod = new Diverse();

        prod.setId((long) id);

        produsDAO.removeProdus(prod);
    }
}
