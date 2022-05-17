package services;

import daos.*;
import entities.*;
import csv.services.CsvWriter;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class CustomerService {
    private final ProdusDAO produsDAO;
    private final ClientDAO clientDAO;
    private final CosDAO cosDAO;
    private final ReviewDAO reviewDAO;
    private final ComandaDAO comandaDAO;

    private final CsvWriter csvWriter;

    public CustomerService(ProdusDAO produsDAO, ClientDAO clientDAO, CosDAO cosDAO,
                           ReviewDAO reviewDAO, ComandaDAO comandaDAO, CsvWriter csvWriter) {
        this.produsDAO = produsDAO;
        this.clientDAO = clientDAO;
        this.cosDAO = cosDAO;
        this.reviewDAO = reviewDAO;
        this.comandaDAO = comandaDAO;
        this.csvWriter = csvWriter;
    }

    // get list of products (ordered by name, price, ascending, descending)
    public List<Produs> getProduse(Produs.OrderCrit orderCrit, Produs.OrderType orderType) {
        return produsDAO.getProduse(orderCrit, orderType);
    }

    public Map<Produs, Integer> getCos(Long userId) {
        return new TreeMap<>(cosDAO.getCos(userId).getProduse());
    }

    // adauga prod in cos, tot cu aceasta functie se pot sterge produse din daca
    public void adaugaProdus(Long userId, Long idProd, Integer cantitate) {
        Produs prod = produsDAO.getById(idProd);
        Cos cos = cosDAO.getCos(userId);
        cos.setCantitateProdus(prod, cantitate);
    }

    public void plaseazaComanda(Long userId, String adresaLivrare) {
        Cos cos = cosDAO.getCos(userId);
        Comanda comanda = new Comanda(-1L, userId, adresaLivrare, cos.getProduse());
        comandaDAO.saveComanda(comanda);

        // reduc stocul produselor
        Map<Produs, Integer> produse = comanda.getProduse();
        for (Map.Entry<Produs, Integer> entry : produse.entrySet()) {
            Long prodId = entry.getKey().getId();
            produsDAO.scadeCantitate(prodId, entry.getValue());
        }
    }

    public void adaugaReview(Long idClient, Long idProdus, int scor,
                             String titlu, String descriere) {
        Review review = new Review(idClient, idProdus, scor, titlu, descriere);

        reviewDAO.saveReview(review);

        // updatez fisierul
        try {
            csvWriter.saveData(Review.class, review, "src/main/resources/csv/reviews.csv");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stergeReview(Long userId, Long produsId) {
        reviewDAO.removeReview(userId, produsId);
    }

    public List<Review> getUserReviews(Long userId) {
        return reviewDAO.getUserReviews(userId);
    }

    public List<Review> getProductReviews(Long productId) {
        return reviewDAO.getProductReviews(productId);
    }

    public List<Comanda> getComenziUser(Long userId) { return comandaDAO.getComenzi(userId); }
}
