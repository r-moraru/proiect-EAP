package daos;

import entities.Client;
import entities.Cos;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CosDAO {
    private Map<Long, Cos> cosDB = new TreeMap<>();

    public CosDAO(ClientDAO clientDAO) {
        List<Client> clienti = clientDAO.getClienti();

        // creez un cos pentru fiecare client
        for (Client client : clienti) {
            cosDB.put(client.getId(), new Cos(client.getId()));
        }
    }

    public Cos getCos(Long userId) {
        // throw exception if userId is not valid
        return cosDB.get(userId);
    }
}
