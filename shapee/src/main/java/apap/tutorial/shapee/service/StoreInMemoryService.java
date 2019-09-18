package apap.tutorial.shapee.service;

import apap.tutorial.shapee.model.StoreModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public StoreModel getStoreById(String idStore) {
        //cek model yang id = idStore
        for (StoreModel model : getStoreList()) {
            if (model.getId().equals(idStore)) return model;
        }
        return null;
    }

}
