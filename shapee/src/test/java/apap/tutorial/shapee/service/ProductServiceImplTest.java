package apap.tutorial.shapee.service;

import apap.tutorial.shapee.model.ProductModel;
import apap.tutorial.shapee.model.StoreModel;
import apap.tutorial.shapee.repository.ProductDb;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {
    @InjectMocks
    ProductService productService = new ProductServiceImpl();
    @Mock
    ProductDb productDb;

    @Test
    public void whenAddValidProductItShouldCallProductRepositorySave() {
        ProductModel newProduct = new ProductModel();
        newProduct.setNama("Produk 1");
        newProduct.setDeskripsi("Desc produk");
        newProduct.getStoreModel();

        productService.addProduct(newProduct);
        verify(productDb, times(1)).save(newProduct);
    }

    @Test
    public void whenDeleteProductItShouldDeleteProductData() {
        ProductModel newProduct = new ProductModel();
        newProduct.setId(4L);
        newProduct.setNama("Produk 1");
        newProduct.setDeskripsi("Desc produk");
        newProduct.getStoreModel();

        productService.addProduct(newProduct);
        productService.deleteProductById(4L);
        assertEquals(false, productDb.existsById(4L));
    }

    @Test
    public void whenGetListProductByStoreIdCalledItShouldReturnAllListProduct() {
        StoreModel store = new StoreModel();
        store.setNama("Toko 1");
        store.setId(1L);
        store.setFollowers(2);
        store.setKeterangan("Desc toko");

        List<ProductModel> listProduct = new ArrayList<>();

        for (int loopTimes = 3; loopTimes > 0; loopTimes--) {
            listProduct.add(new ProductModel());
        }

        for (ProductModel product : listProduct) {
            product.setStoreModel(store);
        }

        when(productService.findAllProductByStoreId(store.getId())).thenReturn(listProduct);
        List<ProductModel> dataFromServiceCall = productService.findAllProductByStoreId(store.getId());
        assertEquals(3, dataFromServiceCall.size());
        verify(productDb, times(1)).findByStoreModelId(store.getId());
    }

    @Test
    public void whenGetAllProductOrderByHargaAscCalledItShouldReturnAllListProduct() {
        StoreModel store = new StoreModel();
        store.setNama("Rose");
        store.setId(1L);
        store.setFollowers(2);
        store.setKeterangan("Dummy");

        List<ProductModel> listProduct = new ArrayList<>();

        for (int loop = 3; loop > 0; loop--) {
            listProduct.add(new ProductModel());
        }

        for (ProductModel product : listProduct) {
            product.setStoreModel(store);
        }

        when(productService.getListProductOrderByHargaAsc(store.getId())).thenReturn(listProduct);
        List<ProductModel> dataFromServiceCall = productService.getListProductOrderByHargaAsc(store.getId());
        assertEquals(3, dataFromServiceCall.size());
        verify(productDb, times(1)).findProductModelByStoreModelIdOrderByHargaAsc(store.getId());
    }

    @Test
    public void whenChangeProductCalledItShouldChangeProductData() {
        ProductModel updatedData = new ProductModel();
        updatedData.setNama("Product 1");
        updatedData.setId(1L);
        updatedData.setDeskripsi("Desc Produk");
        updatedData.setHarga(BigInteger.valueOf(1000000));

        when(productDb.findById(1L)).thenReturn(Optional.of(updatedData));
        when(productService.changeProduct(updatedData)).thenReturn(updatedData);
        ProductModel dataFromServiceCall = productService.changeProduct(updatedData);

        assertEquals("Product 1", dataFromServiceCall.getNama());
        assertEquals((Long) 1L, dataFromServiceCall.getId());
        assertEquals("Desc Produk", dataFromServiceCall.getDeskripsi());
        assertEquals(BigInteger.valueOf(1000000), dataFromServiceCall.getHarga());
    }

    @Test
    public void whenGetProductByIdCalledItShouldReturnSelectedProduct() {
        ProductModel newProduct = new ProductModel();
        newProduct.setNama("Produk 1");
        newProduct.setId(1L);
        newProduct.setStok(BigInteger.valueOf(10));
        newProduct.setDeskripsi("Desc Produk");
        newProduct.getStoreModel();
        productService.addProduct(newProduct);

        when(productService.getProductById(1L)).thenReturn(Optional.of(newProduct));
        Optional<ProductModel> dataFromServiceCall = productService.getProductById(1L);
        verify(productDb, times(1)).findById(1L);
        assertTrue(dataFromServiceCall.isPresent());

        ProductModel dataFromOptional = dataFromServiceCall.get();
        assertEquals("Produk 1", dataFromOptional.getNama());
        assertEquals((Long) 1L, dataFromOptional.getId());
        assertEquals(BigInteger.valueOf(10), dataFromOptional.getStok());
        assertEquals("Desc Produk", dataFromOptional.getDeskripsi());
    }





}
