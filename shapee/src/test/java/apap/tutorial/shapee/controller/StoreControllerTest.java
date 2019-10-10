package apap.tutorial.shapee.controller;

import apap.tutorial.shapee.model.StoreModel;
import apap.tutorial.shapee.service.ProductService;
import apap.tutorial.shapee.service.StoreService;
import org.hamcrest.Matchers;
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
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(StoreController.class)
public class StoreControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Qualifier("storeServiceImpl")
    @MockBean
    StoreService storeService;
    @MockBean
    ProductService productService;

    @Test
    public void whenHomePageRouteAccessItShouldReturnStatusCode200() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk());
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

    @Test
    public void whenViewAllStoreAccessedItShouldShowAllStoreData() throws Exception {
        List<StoreModel> allStoreInTheDatabase = new ArrayList<>();
        for (int loopTimes = 3 ; loopTimes > 0; loopTimes--) {
            allStoreInTheDatabase.add(generateDummyProductModel(loopTimes));
        }
        when(storeService.getStoreList()).thenReturn(allStoreInTheDatabase);
        mockMvc.perform(get("/store/view-all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(Matchers.containsString("Daftar Seluruh Store")))
                .andExpect(content().string(Matchers.containsString("ID Store ")))
                .andExpect(model().attribute("storeList", hasSize(3)))
                .andExpect(model().attribute("storeList", hasItem(
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("nama", is("dummy 1")),
                                hasProperty("keterangan", is("Keterangan 1"))
                        )
                )))
                .andExpect(model().attribute("storeList", hasItem(
                        allOf(
                                hasProperty("id", is(2L)),
                                hasProperty("nama", is("dummy 2")),
                                hasProperty("keterangan", is("Keterangan 2"))
                        )
                )))
                .andExpect(model().attribute("storeList", hasItem(
                        allOf(
                                hasProperty("id", is(3L)),
                                hasProperty("nama", is("dummy 3")),
                                hasProperty("keterangan", is("Keterangan 3"))
                        )
                )));
        verify(storeService, times(1)).getStoreList();
    }

    @Test
    public void whenStoreAddPostFormItShouldSucessfullyReturnToRightView() throws Exception {
        String nama = "Dummy Store";
        String keterangan = "Dummy Keterangan";
        mockMvc.perform(post("/store/add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("nama", nama)
                .param("keterangan", keterangan)
        )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("add-store"))
                .andExpect(model().attribute("nama", is(nama)));
    }

    @Test
    public void whenFindStoreByIdAccessedItShouldReturnSelectedStorePage() throws Exception {
        // Given
        StoreModel store = generateDummyProductModel(1);
        when(storeService.getStoreById(1L)).thenReturn(Optional.of(store));

        // When
        mockMvc.perform(get("/store/view?idStore=1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
                // Then
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("view-store"))
                .andExpect(model().attribute("store", is(store)));
        verify(storeService, times(2)).getStoreById(1L);
    }


}
