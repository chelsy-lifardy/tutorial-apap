package apap.tutorial.shapee.service;

import apap.tutorial.shapee.model.ProductModel;
import apap.tutorial.shapee.repository.ProductDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class ProductRestServiceImpl implements ProductRestService {

    @Autowired
    private ProductDb productDb;

    @Override
    public ProductModel createProduct(ProductModel product) {
        return productDb.save(product);
    }

    @Override
    public List<ProductModel> retrieveListProductModel() {
        return productDb.findAll();
    }

    @Override
    public ProductModel getProductByIdProduct(Long idProduct) {
        Optional<ProductModel> product = productDb.findById(idProduct);
        if (product.isPresent()) {
            return product.get();
        }
        throw new NoSuchElementException();
    }

    @Override
    public ProductModel changeProduct(Long idProduct, ProductModel productModelUpdate) {
        ProductModel product = getProductByIdProduct(idProduct);
        product.setNama(productModelUpdate.getNama());
        product.setDeskripsi(productModelUpdate.getDeskripsi());
        product.setHarga(productModelUpdate.getHarga());
        return productDb.save(product);
    }

    @Override
    public void deleteProduct(Long idProduct) {
        Optional<ProductModel> product = productDb.findById(idProduct);
        if (product.isPresent())
            productDb.deleteById(idProduct);
        else {
            throw new UnsupportedOperationException();
        }
    }
}