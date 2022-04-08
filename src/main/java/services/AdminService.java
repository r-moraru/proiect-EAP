package services;

import daos.ProdusDAO;
import entities.Produs;

public class AdminService {
    private ProdusDAO produsDAO;

    public AdminService(ProdusDAO produsDAO) {
        this.produsDAO = produsDAO;
    }

    // adauga produs
    public void addProduct(Produs product) {
        produsDAO.saveProdus(product);
    }

    // editeaza stoc produs
    public void adaugaCantitate(Long prodId, Integer cantitate) {
        produsDAO.scadeCantitate(prodId, -cantitate);
    }

    // sterge produs
    public void removeProduct(Long prodId) {
        produsDAO.removeProdus(prodId);
    }
}
