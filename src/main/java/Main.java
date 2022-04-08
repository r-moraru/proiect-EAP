import daos.*;
import entities.Comanda;
import entities.Produs;
import services.CustomerService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProdusDAO produsDAO = new ProdusDAO();
        ClientDAO clientDAO = new ClientDAO();
        CosDAO cosDAO = new CosDAO(clientDAO);
        ReviewDAO reviewDAO = new ReviewDAO();
        ComandaDAO comandaDAO = new ComandaDAO();

        CustomerService customerService = new CustomerService(produsDAO, clientDAO, cosDAO,
                                                              reviewDAO, comandaDAO);

        List<Produs> produse = customerService.getProduse(Produs.OrderCrit.PRET, Produs.OrderType.DESCENDING);
        System.out.println(produse);

        customerService.adaugaProdus(1L, produse.get(0), 1);
        customerService.adaugaProdus(1L, produse.get(2), 3);

        System.out.println(customerService.getCos(1L));

        customerService.plaseazaComanda(1L, "Calea Tristetii Numarul 3");

        List<Comanda> comenzi = customerService.getComenziUser(1L);

        System.out.println(comenzi.get(0));
    }
}
