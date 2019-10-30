package apap.tutorial.shapee.service;
import apap.tutorial.shapee.model.StoreModel; // sesuaikan dengan nama package anda
import apap.tutorial.shapee.repository.StoreDb; // sda
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StoreServiceImplTest {
    @InjectMocks
    StoreService storeService = new StoreServiceImpl();
    @Mock
    StoreDb storeDb;

    @Test
    public void whenAddValidStoreItShouldCallStoreRepositorySave() {
        StoreModel newStore = new StoreModel();
        newStore.setNama("seiba");
        newStore.setKeterangan("keterangan");
        newStore.setFollowers(0);

        storeService.addStore(newStore);
        verify(storeDb, times(1)).save(newStore);
    }

    @Test
    public void whenGetStoreListCalledItShouldReturnAllStore() {
        List<StoreModel> allStoreInTheDatabase = new ArrayList<>();
        for (int loopTimes = 3 ; loopTimes > 0; loopTimes--) {
            allStoreInTheDatabase.add(new StoreModel());
        }

        when(storeService.getStoreList()).thenReturn(allStoreInTheDatabase);
        List<StoreModel> dataFromServiceCall = storeService.getStoreList();
        assertEquals(3, dataFromServiceCall.size());
        verify(storeDb, times(1)).findAll();
    }

    @Test
    public void whenGetStoreByIdCalledByExistingDataItShouldReturnCorrectData() {
        StoreModel returnedData = new StoreModel();
        returnedData.setNama("Rose");
        returnedData.setId(1L);
        returnedData.setFollowers(2);
        returnedData.setKeterangan("Dummy");

        when(storeService.getStoreById(1L)).thenReturn(Optional.of(returnedData) );
        Optional<StoreModel> dataFromServiceCall =storeService.getStoreById(1L);
        verify(storeDb, times(1)).findById(1L);
        assertTrue(dataFromServiceCall.isPresent());

        StoreModel dataFromOptional = dataFromServiceCall.get();
        assertEquals("Rose", dataFromOptional.getNama());
        assertEquals((Long) 1L, dataFromOptional.getId());
        assertEquals((Integer) 2, dataFromOptional.getFollowers());
        assertEquals("Dummy", dataFromOptional.getKeterangan());
    }

    @Test
    public void whenChangeStoreCalledItShouldChangeStoreData() {
        StoreModel updatedData = new StoreModel();
        updatedData.setNama("Rose");
        updatedData.setId(1L);
        updatedData.setFollowers(2);
        updatedData.setKeterangan("Dummy");

        when(storeDb.findById(1L)).thenReturn(Optional.of(updatedData) );
        when(storeService.changeStore(updatedData)).thenReturn(updatedData);

        StoreModel dataFromServiceCall = storeService.changeStore(updatedData);
        assertEquals("Rose",dataFromServiceCall.getNama() );
        assertEquals((Long) 1L, dataFromServiceCall.getId());
        assertEquals((Integer) 2, dataFromServiceCall.getFollowers());
        assertEquals("Dummy", dataFromServiceCall.getKeterangan());
    }

    @Test
    public void whenDeleteStoreByIdCalledItShouldDeleteStoreData() {
        StoreModel deleteData = new StoreModel();
        deleteData.setNama("Rose");
        deleteData.setId(4L);
        deleteData.setFollowers(2);
        deleteData.setKeterangan("Dummy");

        storeService.deleteStoreById(4L);
        assertFalse(storeDb.existsById(4L));
    }

}
