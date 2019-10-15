package apap.tutorial.shapee.controller;

import apap.tutorial.shapee.model.StoreModel;
import apap.tutorial.shapee.service.ProductService;
import apap.tutorial.shapee.service.StoreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(StoreController.class)

public class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Qualifier("productServiceImpl")
    @MockBean
    StoreService storeService;
    @MockBean
    ProductService productService;

    @Test
    public void whenProductAddPostFormItShouldSucessfullyReturnToRightView() throws Exception {
        StoreModel store = generateDummyProductModel(1);
        String nama_produk = "Dummy";

        mockMvc.perform(post("/product/add/")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("nama", nama_produk)

        )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("add-product"))
                .andExpect(model().attribute("nama", is(nama_produk)));
    }

    private StoreModel generateDummyProductModel(int count) {
        StoreModel dummyStoreModel = new StoreModel();
        dummyStoreModel.setNama("dummy " + count);
        dummyStoreModel.setListProduct(new ArrayList<>());
        dummyStoreModel.setKeterangan("Keterangan " + count);
        dummyStoreModel.setId((long) count);
        dummyStoreModel.setFollowers(0);
        return dummyStoreModel;
    }

}
