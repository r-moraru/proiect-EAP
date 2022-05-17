import daos.*;
import entities.*;
import csv.services.CsvWriter;
import services.AdminService;
import services.AuditService;
import services.CustomerService;

import java.util.*;

public class Main {
    // TODO: decouple DAO dependencies

    public static void main(String[] args) {
        ProdusDAO produsDAO = new ProdusDAO();
        ClientDAO clientDAO = new ClientDAO();
        CosDAO cosDAO = new CosDAO(clientDAO);
        ReviewDAO reviewDAO = new ReviewDAO();
        ComandaDAO comandaDAO = new ComandaDAO();

        Scanner in = new Scanner(System.in);

        CustomerService customerService = new CustomerService(
                produsDAO, clientDAO, cosDAO,
                reviewDAO, comandaDAO, CsvWriter.getInstance());

        AdminService adminService = new AdminService(produsDAO, CsvWriter.getInstance());

        AuditService auditService;

        try {
            auditService = new AuditService("src/main/resources/csv/audit.csv");
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }

        String line;

        while ((line = in.nextLine()) != null) {
            List<String> command = Arrays.asList(line.split(" "));

            // TODO: creez un command parser ca sa reduc din main
            System.out.println(command.get(0));
            switch (command.get(0)) {
                // COMENZI PENTRU ADMIN
                case "add_chitara" -> {
                    // id, pret, cantitate, numeProd, numeModel, numarCorzi, tip
                    // lemnFretboard, lemnBody, nrFreturi, pentruStangaci
                    long id = Long.parseLong(command.get(1));
                    double pret = Double.parseDouble(command.get(2));
                    int cantitate = Integer.parseInt(command.get(3));
                    String numeProd = command.get(4);
                    String numeModel = command.get(5);
                    int numarCorzi = Integer.parseInt(command.get(6));
                    Chitara.Tip tip = Chitara.Tip.valueOf(command.get(7));
                    Chitara.Lemn lemnFret = Chitara.Lemn.valueOf(command.get(8));
                    Chitara.Lemn lemnBody = Chitara.Lemn.valueOf(command.get(9));
                    int nrFreturi = Integer.parseInt(command.get(10));
                    boolean pentruStangaci = Boolean.parseBoolean(command.get(11));

                    adminService.addChitara(numarCorzi, tip, lemnFret, lemnBody,
                            nrFreturi, pentruStangaci, id, pret, cantitate, numeProd,
                            numeModel);
                }
                case "add_claviatura" -> {
                    // id, pret, cantitate, numeProd, numeModel, numarClape, tip
                    long id = Long.parseLong(command.get(1));
                    double pret = Double.parseDouble(command.get(2));
                    int cantitate = Integer.parseInt(command.get(3));
                    String numeProd = command.get(4);
                    String numeModel = command.get(5);
                    int numarClape = Integer.parseInt(command.get(6));
                    Claviatura.Tip tip = Claviatura.Tip.valueOf(command.get(7));

                    adminService.addClaviatura(numarClape, tip, id, pret,
                            cantitate, numeProd, numeModel);
                }
                case "add_diverse" -> {

                    long id = Long.parseLong(command.get(1));
                    double pret = Double.parseDouble(command.get(2));
                    int cantitate = Integer.parseInt(command.get(3));
                    String numeProd = command.get(4);
                    String numeModel = command.get(5);
                    boolean compChit = Boolean.parseBoolean(command.get(6));
                    boolean compClav = Boolean.parseBoolean(command.get(7));
                    boolean soft = Boolean.parseBoolean(command.get(8));
                    boolean hard = Boolean.parseBoolean(command.get(9));

                    adminService.addDiverse(compChit, compClav, soft, hard,
                            id, pret, cantitate, numeProd, numeModel);
                }
                case "adauga_cantitate" -> {
                    long prodId = Long.parseLong(command.get(1));
                    int cantitate = Integer.parseInt(command.get(2));

                    adminService.adaugaCantitate(prodId, cantitate);
                }
                case "remove_product" -> {
                    long prodId = Long.parseLong(command.get(1));

                    adminService.removeProduct(prodId);
                }
                // COMENZI PENTRU USER
                case "get_produse" -> {
                    Produs.OrderCrit orderCrit = Produs.OrderCrit.valueOf(command.get(1));
                    Produs.OrderType orderType = Produs.OrderType.valueOf(command.get(2));
                    System.out.println(customerService.getProduse(orderCrit, orderType));
                }
                case "adauga_produs" -> {
                    Long userId = Long.parseLong(command.get(1));
                    Long prodId = Long.parseLong(command.get(2));
                    Integer cantitate = Integer.parseInt(command.get(3));

                    customerService.adaugaProdus(userId, prodId, cantitate);
                }
                case "plaseaza_comanda" -> {
                    Long userId = Long.parseLong(command.get(1));
                    // adresa este scrisa pe urmatoarea linie
                    String adresa = in.nextLine();

                    customerService.plaseazaComanda(userId, adresa);
                }
                case "adauga_review" -> {
                    Long idClient = Long.parseLong(command.get(1));
                    Long idProdus = Long.parseLong(command.get(2));
                    Integer scor = Integer.parseInt(command.get(3));
                    // titlu scris pe urmatoarea linie
                    String titlu = in.nextLine();
                    // descriere pe urmatoarea linie
                    String descriere = in.nextLine();

                    customerService.adaugaReview(idClient, idProdus, scor,
                            titlu, descriere);
                }
                case "sterge_review" -> {
                    Long userId = Long.parseLong(command.get(1));
                    Long produsId = Long.parseLong(command.get(2));

                    customerService.stergeReview(userId, produsId);
                }
                case "get_user_reviews" -> {
                    Long userId = Long.parseLong(command.get(1));

                    System.out.println(customerService.getUserReviews(userId));
                }
                case "get_product_reviews" -> {
                    Long prodId = Long.parseLong(command.get(1));

                    System.out.println(customerService.getProductReviews(prodId));
                }
                case "get_comenzi_user" -> {
                    Long userId = Long.parseLong(command.get(1));

                    System.out.println(customerService.getComenziUser(userId));
                }
                default -> {
                    System.out.printf("Comanda invalida");
                    continue;
                }
            }
            try {
                auditService.log(command.get(0));
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
