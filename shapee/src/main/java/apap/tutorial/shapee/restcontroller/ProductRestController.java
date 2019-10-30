package apap.tutorial.shapee.restcontroller;

import apap.tutorial.shapee.model.ProductModel;
import apap.tutorial.shapee.rest.Setting;
import apap.tutorial.shapee.service.ProductRestServiceImpl;
import apap.tutorial.shapee.service.StoreRestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class ProductRestController {

    @Autowired
    private ProductRestServiceImpl productRestServiceImpl;
    @Autowired
    RestTemplate restTemplate;

    //all product
    @GetMapping(value = "/products")
    private List<ProductModel> retrieveListProduct() {
        return productRestServiceImpl.retrieveListProductModel();
    }

    //product
    @GetMapping(value = "/product/{idProduct}")
    private ProductModel retrieveStore(@PathVariable("idProduct") Long idProduct) {
        try {
            return productRestServiceImpl.getProductByIdProduct(idProduct);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "ID Product " + idProduct + " Not Found");
        }
    }

    //add product
    @PostMapping(value = "/product")
    private ProductModel createStore(@Valid @RequestBody ProductModel productModel, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        } else {
            return productRestServiceImpl.createProduct(productModel);
        }
    }

    //delete product
    @DeleteMapping(value = "/product/{idProduct}")
    private ResponseEntity<String> deleteProduct(@PathVariable("idProduct") Long idProduct) {
        try {
            productRestServiceImpl.deleteProduct(idProduct);
            return ResponseEntity.ok("Product with ID " + idProduct + " Deleted");
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Product with ID " + idProduct + " Not Found");
        }
    }

    //update product
    @PutMapping(value = "/product/{idProduct}")
    private ProductModel updateProduct(
            @PathVariable(value = "idProduct") Long idProduct,
            @RequestBody ProductModel product
    ) {
        try {
            return productRestServiceImpl.changeProduct(idProduct, product);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "ID Product " + idProduct + "Not Found");
        }
    }

    @GetMapping(value = "/product/keywords={keywords}")
    private String getProduct(
            @PathVariable(value = "keywords") String keywords
    ) { String keywordUrl =  Setting.etsyUrl + "&&keywords=" + keywords;
        return restTemplate.getForObject(keywordUrl, String.class);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
