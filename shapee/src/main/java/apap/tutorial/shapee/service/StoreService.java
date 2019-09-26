package apap.tutorial.shapee.service;

import apap.tutorial.shapee.model.StoreModel;

import java.util.List;
import java.util.Optional;

public interface StoreService {
    void addStore(StoreModel store);

    List<StoreModel> getStoreList();

    Optional<StoreModel> getStoreById(Long id);

    StoreModel changeStore(StoreModel storeModel);

    void deleteStoreById(Long idStore);
}