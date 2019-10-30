package apap.tutorial.shapee.restcontroller;

import apap.tutorial.shapee.model.StoreModel;
import apap.tutorial.shapee.rest.StoreDetail;
import apap.tutorial.shapee.service.StoreRestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class StoreRestController {

    @Autowired
    private StoreRestServiceImpl storeRestServiceImpl;

    @PostMapping(value = "/store")
    private StoreModel createStore(@Valid @RequestBody StoreModel storeModel, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        } else {
            return storeRestServiceImpl.createStore(storeModel);
        }
    }

    @GetMapping(value = "/store/{idStore}")
    private StoreModel retrieveStore(@PathVariable("idStore") Long idStore) {
        try {
            return storeRestServiceImpl.getStoreByIdStore(idStore);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "ID Store " + idStore + " Not Found");
        }
    }

    @DeleteMapping(value = "/store/{idStore}")
    private ResponseEntity<String> deleteStore(@PathVariable("idStore") Long idStore) {
        try {
            storeRestServiceImpl.deleteStore(idStore);
            return ResponseEntity.ok("Store with ID " + idStore + " Deleted");
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Store with ID " + idStore + " Not Found");
        } catch (UnsupportedOperationException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Store still has product, please delete the product first!");
        }
    }

    @PutMapping(value = "/store/{idStore}")
    private StoreModel updateStore(
            @PathVariable(value = "idStore") Long idStore,
            @RequestBody StoreModel store
    ) {
        try {
            return storeRestServiceImpl.changeStore(idStore, store);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "ID Store " + idStore + "Not Found");
        }
    }

    @GetMapping(value = "/stores")
    private List<StoreModel> retrieveListStore() {
        return storeRestServiceImpl.retrieveListStoreModel();
    }

    @GetMapping(value = "/store/{idStore}/status")
    private Mono<String> getStatus(@PathVariable Long idStore) {
        return storeRestServiceImpl.getStatus(idStore);
    }

    @GetMapping(value = "/store/full")
    private Mono<StoreDetail> postStatus() {
        return storeRestServiceImpl.postStatus();
    }
}
