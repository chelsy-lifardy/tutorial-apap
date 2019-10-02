package apap.tutorial.shapee.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import apap.tutorial.shapee.model.ProductModel;

public interface ProductService {
    void addProduct(ProductModel product);

    List<ProductModel> findAllProductByStoreId(Long storeId);

    Optional<ProductModel> getProductById(Long productId);

    ProductModel changeProduct(ProductModel product);

    void deleteProductById(Long productId);

    List<ProductModel> getListProductOrderByHargaAsc(Long storeId);
}
