package apap.tutorial.shapee.service;

import apap.tutorial.shapee.model.ProductModel;
import apap.tutorial.shapee.repository.ProductDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDb productDb;

    @Override
    public void addProduct(ProductModel product) {
        productDb.save(product);
    }

    @Override
    public List<ProductModel> findAllProductByStoreId(Long storeId) {
        return productDb.findByStoreModelId(storeId);
    }

    @Override
    public Optional<ProductModel> getProductById(Long id) {
        return productDb.findById(id);
    }

    @Override
    public ProductModel changeProduct(ProductModel product) {
        ProductModel targetProduct = productDb.findById(product.getId()).get();

        targetProduct.setNama(product.getNama());
        targetProduct.setDeskripsi(product.getDeskripsi());
        targetProduct.setHarga(product.getHarga());
        targetProduct.setStok(product.getStok());

        productDb.save(targetProduct);

        return targetProduct;
    }

    @Override
    public void deleteProductById(Long storeId) {
        productDb.deleteById(storeId);
    }
}