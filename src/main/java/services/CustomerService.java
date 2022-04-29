package services;

import daos.*;
import entities.*;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class CustomerService {
    // get list of products (ordered by name, price, ascending, descending)
    private final ProdusDAO produsDAO;
    private final ClientDAO clientDAO;
    private final CosDAO cosDAO;
    private final ReviewDAO reviewDAO;
    private final ComandaDAO comandaDAO;

    public CustomerService(ProdusDAO produsDAO, ClientDAO clientDAO, CosDAO cosDAO,
                           ReviewDAO reviewDAO, ComandaDAO comandaDAO) {
        this.produsDAO = produsDAO;
        this.clientDAO = clientDAO;
        this.cosDAO = cosDAO;
        this.reviewDAO = reviewDAO;
        this.comandaDAO = comandaDAO;
    }

    public List<Produs> getProduse(Produs.OrderCrit orderCrit, Produs.OrderType orderType) {
        return produsDAO.getProduse(orderCrit, orderType);
    }

    // get pentru cosul de cumparaturi
    public Map<Produs, Integer> getCos(Long userId) {
        return new TreeMap<>(cosDAO.getCos(userId).getProduse());
    }

    // adauga prod in cos, tot cu aceasta functie se pot sterge produse din daca
    public void adaugaProdus(Long userId, Produs prod, Integer cantitate) {
        Cos cos = cosDAO.getCos(userId);
        cos.setCantitateProdus(prod, cantitate);
    }

    // TODO: pentru fiecare produs din comanda, scade din stoc
    // plaseaza comanda
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

    // adauga review
    public void adaugaReview(Review review) {
        reviewDAO.saveReview(review);
    }

    // sterge review
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
