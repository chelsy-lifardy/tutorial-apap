package apap.tutorial.shapee.controller;

import apap.tutorial.shapee.model.ProductModel;
import apap.tutorial.shapee.model.StoreModel;
import apap.tutorial.shapee.service.ProductService;
import apap.tutorial.shapee.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;


@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Qualifier("storeServiceImpl")
    @Autowired
    private StoreService storeService;

    @RequestMapping(value = "product/add/{storeId}", method = RequestMethod.GET)
    public String addProductFormPage(@PathVariable(value = "storeId") Long storeId, Model model) {
        ArrayList<ProductModel> listProduct = new ArrayList<>();
        ProductModel product = new ProductModel();

        StoreModel store = storeService.getStoreById(storeId).get();
        listProduct.add(product);
        store.setListProduct(listProduct);

        model.addAttribute("store", store);
        model.addAttribute("page", "Add Product");

        return "form-add-product";
    }

    @RequestMapping(value = "product/add", params = {"save"}, method = RequestMethod.POST)
    private String addProductSubmit(@ModelAttribute(value = "store") StoreModel store, Model model) {
        StoreModel storeObj = storeService.getStoreById(store.getId()).get();

        for (ProductModel product : store.getListProduct()) {
            product.setStoreModel(storeObj);
            productService.addProduct(product);
        }
        return "add-product";
    }

    @RequestMapping(value = "/product/add", params = {"addRow"}, method = RequestMethod.POST)
    private String addRowProduct(
            @ModelAttribute StoreModel store,
            Model model
    ) {
        if (store.getListProduct() == null) {
            store.setListProduct(new ArrayList<>());
        }

        ProductModel product = new ProductModel();
        store.getListProduct().add(product);

        model.addAttribute("store", store);

        return "form-add-product";
    }

    @PostMapping(value = "/product/add", params = {"deleteRow"})
    private String deleteRowProduct(
            @ModelAttribute(value = "store") StoreModel store,
            final HttpServletRequest req
    ) {
        final Integer rowId = Integer.valueOf(req.getParameter("deleteRow"));
        store.getListProduct().remove(rowId.intValue());
        return "form-add-product";
    }

    @RequestMapping(value = {"product/change", "product/change/{idProduct}"}, method = RequestMethod.GET)
    public String changeStoreFormPage(@PathVariable(required = false) Long idProduct, Model model) {
        model.addAttribute("page", "Change Product");
        if (idProduct == null || !isProductExist(idProduct)) {
            model.addAttribute("informasi", "Produk tidak ditemukan!");
            return "error-not-found";
        }
        ProductModel existingProduct = productService.getProductById(idProduct).get();
        model.addAttribute("product", existingProduct);

        return "form-change-product";
    }

    @RequestMapping(value = "product/change/{idProduct}", method = RequestMethod.POST)
    public String changeStoreFormSubmit(@PathVariable Long idProduct, @ModelAttribute ProductModel product, Model model) {
        ProductModel newProductData = productService.changeProduct(product);
        model.addAttribute("product", newProductData);
        model.addAttribute("page", "Change Product");

        return "change-product";
    }

    @RequestMapping(value = "product/delete", method = RequestMethod.POST)
    public String deleteProduct(@ModelAttribute StoreModel store, Model model) {
        for (ProductModel product: store.getListProduct()) {
            productService.deleteProductById(product.getId());
        }
        model.addAttribute("informasi", "Produk berhasil dihapus");
        model.addAttribute("page", "Delete Product");
        return "delete-product";
    }

    private boolean isProductExist(Long id) {
        return productService.getProductById(id).isPresent();
    }

}