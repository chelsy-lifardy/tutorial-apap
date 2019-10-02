package apap.tutorial.shapee.service;

import apap.tutorial.shapee.model.StoreModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StoreInMemoryService implements StoreService {
    private List<StoreModel> listStore;

    public StoreInMemoryService() {
        listStore = new ArrayList<>();
    }

    @Override
    public void addStore(StoreModel store) {
        listStore.add(store);
    }

    @Override
    public List<StoreModel> getStoreList() {
        return listStore;
    }

    @Override
    public Optional<StoreModel> getStoreById(Long id) {
        return Optional.empty();
    }

    @Override
    public StoreModel changeStore(StoreModel storeModel) {
        return null;
    }

    @Override
    public void deleteStoreById(Long idStore) {
        return;
    }
}