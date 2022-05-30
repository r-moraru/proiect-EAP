package daos;

import entities.Chitara;
import entities.Claviatura;
import entities.Diverse;
import entities.Produs;
import csv.services.CsvReader;
import repositories.ChitaraRepo;
import repositories.ClaviaturaRepo;
import repositories.DiverseRepo;

import java.util.*;

import static java.util.Collections.reverse;

public class ProdusDAO {
    private ChitaraRepo chitaraRepo;
    private ClaviaturaRepo claviaturaRepo;
    private DiverseRepo diverseRepo;

    public ProdusDAO(ChitaraRepo chitaraRepo, ClaviaturaRepo claviaturaRepo,
                    DiverseRepo diverseRepo) {
        this.chitaraRepo = chitaraRepo;
        this.claviaturaRepo = claviaturaRepo;
        this.diverseRepo = diverseRepo;
    }

    public List<Produs> getProduse(Produs.OrderCrit orderCrit, Produs.OrderType orderType) {
        List<Produs> ret = new ArrayList<>();

        ret.addAll(chitaraRepo.getAll());
        ret.addAll(claviaturaRepo.getAll());
        ret.addAll(diverseRepo.getAll());

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


    public Produs getChitaraById(Long id) {
        return chitaraRepo.getById(Math.toIntExact(id));
    }

    public Produs getClaviaturaById(Long id) {
        return claviaturaRepo.getById(Math.toIntExact(id));
    }

    public Produs getDiverseById(Long id) {
        return diverseRepo.getById(Math.toIntExact(id));
    }

    public void saveProdus(Produs produs) {
        if (produs instanceof Chitara)
            chitaraRepo.insertChitara((Chitara)produs);
        if (produs instanceof Claviatura)
            claviaturaRepo.insertClaviatura((Claviatura)produs);
        if (produs instanceof Diverse)
            diverseRepo.insertDiverse((Diverse)produs);
    }

    public void removeProdus(Produs produs) {
        if (produs instanceof Chitara)
            chitaraRepo.deleteById(Math.toIntExact(produs.getId()));
        if (produs instanceof Claviatura)
            claviaturaRepo.deleteById(Math.toIntExact(produs.getId()));
        if (produs instanceof Diverse)
            diverseRepo.deleteById(Math.toIntExact(produs.getId()));
    }

    public void scadeCantitateChitara(Integer id, Integer cantitate) {
        Chitara chitara = chitaraRepo.getById(id);
        chitara.setCantitate(Math.max(chitara.getCantitate() - cantitate, 0));
        chitaraRepo.updateChitara(chitara);
    }

    public void scadeCantitateClaviatura(Integer id, Integer cantitate) {
        Claviatura claviatura = claviaturaRepo.getById(id);
        claviatura.setCantitate(Math.max(claviatura.getCantitate() - cantitate, 0));
        claviaturaRepo.updateClaviatura(claviatura);
    }

    public void scadeCantitateDiverse(Integer id, Integer cantitate) {
        Diverse diverse = diverseRepo.getById(id);
        diverse.setCantitate(Math.max(diverse.getCantitate() - cantitate, 0));
        diverseRepo.updateDiverse(diverse);
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