package apap.tutorial.shapee.repository;

import apap.tutorial.shapee.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDb extends JpaRepository<ProductModel, Long> {
    List<ProductModel> findByStoreModelId(Long storeId);

    Optional<ProductModel> findById(Long productId);

    void deleteById(Long productId);

    List<ProductModel> findProductModelByStoreModelIdOrderByHargaAsc(Long storeId);
}