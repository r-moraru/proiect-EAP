package daos;

import entities.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    private List<Client> clientDB = new ArrayList<>();

    public ClientDAO() {
        clientDB.add(new Client(1, "Popescu", "Ioan", "pop.ioan@mail.com"));
        clientDB.add(new Client(2, "Tudosescu", "Gabriela", "tudosescu_gabriela@mail.com"));
        clientDB.add(new Client(3, "Repanovici", "Vlad", "v.repanovici@mail.com"));
    }

    public List<Client> getClienti() {
        return new ArrayList<>(clientDB);
    }
}
