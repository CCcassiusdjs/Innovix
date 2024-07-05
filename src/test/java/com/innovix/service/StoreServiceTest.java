package com.innovix.service;

import com.innovix.entity.Store;
import com.innovix.repository.StoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StoreServiceTest {

    @Mock
    private StoreRepository storeRepository;

    @InjectMocks
    private StoreService storeService;

    private Store store1;
    private Store store2;

    @BeforeEach
    void setUp() {
        store1 = new Store();
        store1.setId(1L);
        store1.setStoreName("Store A");
        store1.setStoreCnpj("12345678901234");

        store2 = new Store();
        store2.setId(2L);
        store2.setStoreName("Store B");
        store2.setStoreCnpj("56789012345678");
    }

    @Test
    void testListAll() {
        when(storeRepository.findAll()).thenReturn(Arrays.asList(store1, store2));

        List<Store> stores = storeService.listAll();

        assertEquals(2, stores.size());
        assertEquals("Store A", stores.get(0).getStoreName());
        assertEquals("Store B", stores.get(1).getStoreName());
        verify(storeRepository, times(1)).findAll();
    }

    @Test
    void testSave() {
        when(storeRepository.save(any(Store.class))).thenReturn(store1);

        Store savedStore = storeService.save(store1);

        assertEquals("Store A", savedStore.getStoreName());
        verify(storeRepository, times(1)).save(store1);
    }

    @Test
    void testFindById() {
        when(storeRepository.findById(1L)).thenReturn(Optional.of(store1));

        Store foundStore = storeService.findById(1L);

        assertEquals("Store A", foundStore.getStoreName());
        verify(storeRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByName() {
        when(storeRepository.findByStoreName("Store A")).thenReturn(store1);

        Store foundStore = storeService.findByName("Store A");

        assertEquals("12345678901234", foundStore.getStoreCnpj());
        verify(storeRepository, times(1)).findByStoreName("Store A");
    }

    @Test
    void testFindByCnpj() {
        when(storeRepository.findByStoreCnpj("12345678901234")).thenReturn(store1);

        Store foundStore = storeService.findByCnpj("12345678901234");

        assertEquals("Store A", foundStore.getStoreName());
        verify(storeRepository, times(1)).findByStoreCnpj("12345678901234");
    }

    @Test
    void testFindByCity() {
        when(storeRepository.findByAddressCity("City")).thenReturn(Arrays.asList(store1, store2));

        List<Store> stores = storeService.findByCity("City");

        assertEquals(2, stores.size());
        verify(storeRepository, times(1)).findByAddressCity("City");
    }

    @Test
    void testFindByState() {
        when(storeRepository.findByAddressState("State")).thenReturn(Arrays.asList(store1, store2));

        List<Store> stores = storeService.findByState("State");

        assertEquals(2, stores.size());
        verify(storeRepository, times(1)).findByAddressState("State");
    }

    @Test
    void testFindByCountry() {
        when(storeRepository.findByAddressCountry("Country")).thenReturn(Arrays.asList(store1, store2));

        List<Store> stores = storeService.findByCountry("Country");

        assertEquals(2, stores.size());
        verify(storeRepository, times(1)).findByAddressCountry("Country");
    }

    @Test
    void testFindByEmployeeId() {
        when(storeRepository.findByEmployeeId(1L)).thenReturn(Arrays.asList(store1, store2));

        List<Store> stores = storeService.findByEmployeeId(1L);

        assertEquals(2, stores.size());
        verify(storeRepository, times(1)).findByEmployeeId(1L);
    }

    @Test
    void testDelete() {
        doNothing().when(storeRepository).deleteById(1L);

        storeService.delete(1L);

        verify(storeRepository, times(1)).deleteById(1L);
    }
}
