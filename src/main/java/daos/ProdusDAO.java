package daos;

import entities.Chitara;
import entities.Claviatura;
import entities.Diverse;
import entities.Produs;

import java.util.*;

import static java.util.Collections.reverse;

public class ProdusDAO {
    private Map<Long, Produs> produsDB = new TreeMap<>();

    public ProdusDAO() {
        Long id = 1L;
        produsDB.put(id, new Chitara(6, Chitara.Tip.ELECTRICA, Chitara.Lemn.ROSEWOOD, Chitara.Lemn.MAHOGANY, 22, false,
                1, 4199, 31, "Fender", "Jazzmaster"));
        produsDB.put(id+1, new Claviatura(88, Claviatura.Tip.MIDI_CONTROLLER,
                id+1, 1800, 78, "Arturia", "Keylab 88"));
        produsDB.put(id+2, new Diverse(true, false, false, true,
                id+2, 59.90, 138, "D'Addario", "Pro-Arte Nylon Strings"));
    }

    public List<Produs> getProduse(Produs.OrderCrit orderCrit, Produs.OrderType orderType) {
        List<Produs> ret = new ArrayList<>();

        for (Map.Entry<Long, Produs> entry : produsDB.entrySet()) {
            ret.add(entry.getValue());
        }

        if (orderCrit == Produs.OrderCrit.NUME) {
            NameComparator nameComparator = new NameComparator();
            ret.sort(nameComparator);
        }
        else if (orderCrit == Produs.OrderCrit.PRET) {
            PriceComparator priceComparator = new PriceComparator();
            ret.sort(priceComparator);
        }

        if (orderType == Produs.OrderType.DESCENDING) {
            reverse(ret);
        }

        return ret;
    }

    public void saveProdus(Produs produs) {
        produsDB.put(produs.getId(), produs);
    }

    public void removeProdus(Long prodId) {
        produsDB.remove(prodId);
    }

    public void scadeCantitate(Long produsId, Integer cantitate) {
        Produs prod = produsDB.get(produsId);
        prod.setCantitate(Math.max(prod.getCantitate() - cantitate, 0));
    }
}

class NameComparator implements Comparator<Produs> {
    @Override
    public int compare(Produs a, Produs b) {
        return (a.getNumeProducator() + a.getNumeModel()).compareToIgnoreCase(b.getNumeProducator() + b.getNumeModel());
    }
}

class PriceComparator implements Comparator<Produs> {
    @Override
    public int compare(Produs a, Produs b) {
        return a.getPret().compareTo(b.getPret());
    }
}