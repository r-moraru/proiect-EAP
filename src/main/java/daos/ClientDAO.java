package daos;

import entities.Client;
import csv.services.CsvReader;
import repositories.ClientRepo;

import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    private ClientRepo clientRepo;

    public ClientDAO(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    public List<Client> getClienti() {
        return clientRepo.getAll();
    }
}
