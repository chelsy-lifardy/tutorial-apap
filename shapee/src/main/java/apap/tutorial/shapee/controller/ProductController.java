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


@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Qualifier("storeServiceImpl")
    @Autowired
    private StoreService storeService;

    @RequestMapping(value = "product/add/{storeId}", method = RequestMethod.GET)
    public String addProductFormPage(@PathVariable(value = "storeId") Long storeId, Model model) {
        ProductModel product = new ProductModel();
        StoreModel store = storeService.getStoreById(storeId).get();
        product.setStoreModel(store);

        model.addAttribute("product", product);
        model.addAttribute("page", "Add Product");

        return "form-add-product";
    }

    @RequestMapping(value = "product/add", method = RequestMethod.POST)
    private String addProductSubmit(@ModelAttribute ProductModel productModel, Model model) {
        productService.addProduct(productModel);
        model.addAttribute("nama", productModel.getNama());
        model.addAttribute("page", "Add Product");

        return "add-product";
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