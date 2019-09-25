package apap.tutorial.shapee.controller;

import apap.tutorial.shapee.model.StoreModel;
import apap.tutorial.shapee.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StoreController {
    @Autowired
    private StoreService storeService;

    //url mapping view
    @RequestMapping("/store/add")
    public String add(
            //Request parameter untuk dipass
            @RequestParam(value = "idStore", required = true) String idStore,
            @RequestParam(value = "nama", required = true) String nama,
            @RequestParam(value = "keterangan", required = true) String keterangan,
            @RequestParam(value = "followers", required = true) int followers,
            Model model)
    {
        //membuat objek store model
        StoreModel store = new StoreModel(idStore, nama, keterangan, followers);

        //memanggil service addStore
        storeService.addStore(store);

        //memasukkan atribut nama pada view template dengan variable nama
        model.addAttribute("nama", nama);

        //return view template add-store
        return "add-store";
    }


    @RequestMapping("/store/view")
    public String view (
            //request Param untuk dipass
            @RequestParam(value = "idStore", required = false) String idStore, Model model) {

        //mengambil objek store yang dituju berdasarkan id
        StoreModel store = storeService.getStoreById(idStore);
        if (checkNull(store, model)) {
            return "id-not-found";
        }
        //add objek store ke "store" untuk dirender
        model.addAttribute("store", store);

        //return view template view-store
        return "view-store";
    }

    @RequestMapping("/store/view-all")
    public String viewAll(Model model) {

        //mengambil semua objek restoran
        List<StoreModel> storeList = storeService.getStoreList();

        //add objek store ke "store" untuk dirender
        model.addAttribute("storeList", storeList);

        //return view template view-store
        return "view-all-store";
    }

    @GetMapping(value = {"/store/view/id-store","/store/view/id-store/{idStore}"})
    public String viewById(
            @PathVariable(value = "idStore", required = false) String idStore, Model model)
    {
        //mengambil objek store yang dituju berdasarkan id
        StoreModel store = storeService.getStoreById(idStore);

        if (checkNull(store, model)) {
            return "id-not-found";
        }
        //add objek store ke "store" untuk dirender
        model.addAttribute("store", store);
        return "view-store";
    }

    @GetMapping(value = "/store/update/id-store/{idStore}/keterangan/{keterangan}")
    public String update(
            @PathVariable(value = "idStore") String idStore,
            @PathVariable(value = "keterangan") String keterangan,
            Model model)
    {
        StoreModel store = storeService.getStoreById(idStore);
        if (checkNull(store, model)) {
            return "id-not-found";
        } else {
            store.setKeterangan(keterangan);
        }
        model.addAttribute("store", store);
        model.addAttribute("informasi", "Store berhasil diperbarui");
        return "view-store";
    }

    @GetMapping(value = {"/store/delete/id" ,"/store/delete/id/{idStore}"})
    public String delete(
            @PathVariable(value = "idStore", required = false) String idStore,
            Model model
    )
    {
        List<StoreModel> storeList = storeService.getStoreList();
        model.addAttribute("storeList", storeList);

        StoreModel store = storeService.getStoreById(idStore);
        if (checkNull(store, model)) {
            return "id-not-found";
        }else {
            storeList.remove(store);
            model.addAttribute("informasi", "Store berhasil dihapus");
        }
        return "view-all-store";
    }

    public boolean checkNull(StoreModel store, Model model) {
        if (store == null) {
            model.addAttribute("informasi", "Error: ID Store tidak ada");
            return true;
        }
        return false;
    }

}
