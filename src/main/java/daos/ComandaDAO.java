package daos;

import entities.Comanda;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ComandaDAO {
    // map de la id client la lista de comenzi
    private Map<Long, List<Comanda>> comandaDB = new TreeMap<>();

    public void saveComanda(Comanda comanda) {
        Long id = comandaDB.size()+1L;
        comanda.setId(id);

        if (!comandaDB.containsKey(comanda.getIdUser())) {
            comandaDB.put(comanda.getIdUser(), new ArrayList<>());
        }

        comandaDB.get(comanda.getIdUser()).add(comanda);
    }

    public List<Comanda> getComenzi(Long userId) {
        return comandaDB.get(userId);
    }
}
