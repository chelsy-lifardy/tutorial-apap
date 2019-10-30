package apap.tutorial.shapee.service;

import apap.tutorial.shapee.model.StoreModel;
import apap.tutorial.shapee.repository.StoreDb;
import apap.tutorial.shapee.rest.Setting;
import apap.tutorial.shapee.rest.StoreDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.thymeleaf.expression.Ids;
import reactor.core.publisher.Mono;

import java.nio.channels.MulticastChannel;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class StoreRestServiceImpl implements StoreRestService {

    private final WebClient webClient;

    @Autowired
    private StoreDb storeDb;

    @Override
    public StoreModel createStore(StoreModel store) {
        return storeDb.save(store);
    }

    @Override
    public List<StoreModel> retrieveListStoreModel() {
        return storeDb.findAllByOrderByNamaAsc();
    }

    @Override
    public StoreModel getStoreByIdStore(Long idStore) {
        Optional<StoreModel> store = storeDb.findById(idStore);
        if (store.isPresent()) {
            return store.get();
        }
        throw new NoSuchElementException();
    }

    @Override
    public StoreModel changeStore(Long idStore, StoreModel storeModelUpdate) {
        StoreModel store = getStoreByIdStore(idStore);
        store.setNama(storeModelUpdate.getNama());
        store.setKeterangan(storeModelUpdate.getKeterangan());
        store.setFollowers(storeModelUpdate.getFollowers());
        return storeDb.save(store);
    }

    @Override
    public void deleteStore(Long idStore) {
        StoreModel store = getStoreByIdStore(idStore);
        if (store.getListProduct().size() == 0) {
            storeDb.delete(store);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    //customer service


    public StoreRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.storeUrl).build();
    }

    @Override
    public Mono<String> getStatus(Long idStore) {
        return this.webClient.get().uri("/rest/store/status/" + idStore).retrieve().bodyToMono(String.class);
    }

    @Override
    public Mono<StoreDetail> postStatus() {
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("keterangan", "toko elektronik");
        data.add("followers", "200");
        return this.webClient.post().uri("/rest/store/full")
                .syncBody(data)
                .retrieve()
                .bodyToMono(StoreDetail.class);
    }
}
