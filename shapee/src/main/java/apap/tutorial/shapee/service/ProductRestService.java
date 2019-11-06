package apap.tutorial.shapee.service;

import apap.tutorial.shapee.model.ProductModel;

import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductRestService {
    ProductModel createProduct(ProductModel product);
    List<ProductModel> retrieveListProductModel();
    ProductModel getProductByIdProduct(Long idProduct);
    ProductModel changeProduct(Long idProduct, ProductModel productModelUpdate);
    void deleteProduct(Long idProduct);

}
