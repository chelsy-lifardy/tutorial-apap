package apap.tutorial.shapee.service;
import java.util.List;
import apap.tutorial.shapee.model.StoreModel;

public interface StoreService {

    //menambah store
    void addStore(StoreModel store);

    //mendapatkan data semua store yang tersimpan
    List<StoreModel> getStoreList();

    //mendapatkan data store berdasarkan id
    StoreModel getStoreById(String idStore);
}
