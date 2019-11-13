package apap.tutorial.shapee.controller;

import apap.tutorial.shapee.model.StoreModel;
import apap.tutorial.shapee.model.ProductModel;
import apap.tutorial.shapee.service.ProductService;
import apap.tutorial.shapee.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
public class StoreController {
    @Qualifier("storeServiceImpl")
    @Autowired
    private StoreService storeService;

    @Autowired
    private ProductService productService;

//    @RequestMapping("/")
//    private String home(Model model) {
//        model.addAttribute("page", "Home");
//        return "home";
//    }

    @RequestMapping(value = "/store/add", method = RequestMethod.GET)
    public String addStoreFormPage(Model model) {
        model.addAttribute("page", "Add Store");
        StoreModel newStore = new StoreModel();
        newStore.setFollowers(0);
        model.addAttribute("store", newStore);
        return "form-add-store";
    }

    @RequestMapping(value = "/store/add", method = RequestMethod.POST)
    private String addStoreSubmit(@ModelAttribute StoreModel store, Model model) {
        model.addAttribute("page", "Add Store");
        storeService.addStore(store);
        model.addAttribute("nama", store.getNama());
        return "add-store";
    }

    @RequestMapping(value="/store/view", method = RequestMethod.GET)
    public String view(
            @RequestParam(value = "idStore", required = false) Long idStore, Model model) {

        model.addAttribute("page", "View Store");
        if (idStore == null || !isStoreExist(idStore)) {
            model.addAttribute("informasi", "Toko tidak ditemukan!");
            return "error-not-found";
        }

        StoreModel store = storeService.getStoreById(idStore).get();

        List<ProductModel> productList = productService.getListProductOrderByHargaAsc(idStore);
        store.setListProduct(productList);

        model.addAttribute("store", store);

        return "view-store";
    }

    @RequestMapping("/store/view-all")
    public String view(Model model) {
        List<StoreModel> storeList = storeService.getStoreList();
        storeList.sort(Comparator.comparing(StoreModel::getNama));
        model.addAttribute("storeList", storeList);
        model.addAttribute("page", "View All Store");

        //return view template view-store
        return "view-all-store";
    }

    @RequestMapping(value = {"store/change", "store/change/{idStore}"}, method = RequestMethod.GET)
    public String changeStoreFormPage(@PathVariable (required = false) Long idStore, Model model) {
        model.addAttribute("page", "Change Store");
        if (idStore == null || !isStoreExist(idStore)) {
            model.addAttribute("informasi", "Toko tidak ditemukan!");
            return "error-not-found";
        }

        StoreModel existingStore = storeService.getStoreById(idStore).get();
        model.addAttribute("store", existingStore);

        return "form-change-store";
    }

    @RequestMapping(value = "store/change/{idStore}", method = RequestMethod.POST)
    public String changeStoreFormSubmit(@PathVariable Long idStore, @ModelAttribute StoreModel store, Model model) {
        StoreModel newStoreData = storeService.changeStore(store);
        model.addAttribute("store", newStoreData);
        model.addAttribute("page", "Change Store");

        return "change-store";
    }

    @RequestMapping(value = {"store/delete","store/delete/{idStore}"}, method = RequestMethod.GET)
    public String deleteStore(@PathVariable (required = false) Long idStore, Model model) {
        model.addAttribute("page", "Delete Store");
        if (idStore == null || !isStoreExist(idStore)) {
            model.addAttribute("informasi", "Toko tidak ditemukan!");
            return "error-not-found";
        }
        StoreModel existingStore = storeService.getStoreById(idStore).get();

        List<ProductModel> productList = productService.findAllProductByStoreId(idStore);
        model.addAttribute("store", existingStore);
        model.addAttribute("productList", productList);

        if (productService.findAllProductByStoreId(idStore).size() != 0) {
            model.addAttribute("informasi", "penghapusan data tidak berhasil karena toko masih menyimpan produk");
            return "view-store";
        }
        storeService.deleteStoreById(idStore);
        return "delete-store";
    }

    private boolean isStoreExist(Long id) {
        return storeService.getStoreById(id).isPresent();
    }
}
