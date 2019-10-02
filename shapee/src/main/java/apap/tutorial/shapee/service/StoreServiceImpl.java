package apap.tutorial.shapee.service;

import apap.tutorial.shapee.model.StoreModel;
import apap.tutorial.shapee.repository.StoreDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StoreServiceImpl implements StoreService {
    @Autowired
    StoreDb storeDb;

    @Override
    public Optional<StoreModel> getStoreById(Long id) {
        return storeDb.findById(id);
    }

    @Override
    public void addStore(StoreModel storeModel) {
        storeDb.save(storeModel);
    }

    @Override
    public List<StoreModel> getStoreList() {
        return storeDb.findAll();
    }

    @Override
    public StoreModel changeStore(StoreModel store) {
        StoreModel targetStore = storeDb.findById(store.getId()).get();

        targetStore.setNama(store.getNama());
        targetStore.setKeterangan(store.getKeterangan());

        storeDb.save(targetStore);

        return targetStore;
    }

    @Override
    public void deleteStoreById(Long idStore) {
        storeDb.deleteById(idStore);
    }
}