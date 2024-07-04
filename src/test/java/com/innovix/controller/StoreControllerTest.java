package com.innovix.controller;

import com.innovix.dto.StoreDTO;
import com.innovix.entity.Store;
import com.innovix.mapper.StoreMapper;
import com.innovix.usecase.EmployeeUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StoreControllerTest {

    @Mock
    private EmployeeUseCase employeeUseCase;

    @Mock
    private StoreMapper storeMapper;

    @InjectMocks
    private StoreController storeController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(storeController).build();
    }

    private Store createStore() {
        Store store = new Store();
        store.setId(1L);
        store.setStoreName("Store1");
        return store;
    }

    private StoreDTO createStoreDTO() {
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setStoreId(1L);
        storeDTO.setStoreName("Store1");
        return storeDTO;
    }

    @Test
    @WithMockUser(authorities = "CUSTOMER")
    void testListAll() throws Exception {
        Store store = createStore();
        StoreDTO storeDTO = createStoreDTO();

        when(employeeUseCase.listAllStores()).thenReturn(Collections.singletonList(store));
        when(storeMapper.toDto(any(Store.class))).thenReturn(storeDTO);

        mockMvc.perform(get("/api/stores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].storeName").value("Store1"));
    }

    @Test
    @WithMockUser(authorities = "EMPLOYEE")
    void testSave() throws Exception {
        Store store = createStore();
        StoreDTO storeDTO = createStoreDTO();

        when(storeMapper.toEntity(any(StoreDTO.class))).thenReturn(store);
        when(employeeUseCase.createStore(any(Store.class))).thenReturn(store);
        when(storeMapper.toDto(any(Store.class))).thenReturn(storeDTO);

        mockMvc.perform(post("/api/stores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"storeName\": \"Store1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.storeName").value("Store1"));
    }

    @Test
    @WithMockUser(authorities = "CUSTOMER")
    void testGetById() throws Exception {
        Store store = createStore();
        StoreDTO storeDTO = createStoreDTO();

        when(employeeUseCase.getStoreById(1L)).thenReturn(store);
        when(storeMapper.toDto(any(Store.class))).thenReturn(storeDTO);

        mockMvc.perform(get("/api/stores/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.storeName").value("Store1"));
    }

    @Test
    @WithMockUser(authorities = "CUSTOMER")
    void testGetByName() throws Exception {
        Store store = createStore();
        StoreDTO storeDTO = createStoreDTO();

        when(employeeUseCase.getStoreByName("Store1")).thenReturn(store);
        when(storeMapper.toDto(any(Store.class))).thenReturn(storeDTO);

        mockMvc.perform(get("/api/stores/name/Store1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.storeName").value("Store1"));
    }

    @Test
    @WithMockUser(authorities = "CUSTOMER")
    void testGetByCnpj() throws Exception {
        Store store = createStore();
        StoreDTO storeDTO = createStoreDTO();

        when(employeeUseCase.getStoreByCnpj("123456789")).thenReturn(store);
        when(storeMapper.toDto(any(Store.class))).thenReturn(storeDTO);

        mockMvc.perform(get("/api/stores/cnpj/123456789"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.storeName").value("Store1"));
    }

    @Test
    @WithMockUser(authorities = "CUSTOMER")
    void testListByCity() throws Exception {
        Store store = createStore();
        StoreDTO storeDTO = createStoreDTO();

        when(employeeUseCase.listStoresByCity("City1")).thenReturn(Collections.singletonList(store));
        when(storeMapper.toDto(any(Store.class))).thenReturn(storeDTO);

        mockMvc.perform(get("/api/stores/city/City1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].storeName").value("Store1"));
    }

    @Test
    @WithMockUser(authorities = "CUSTOMER")
    void testListByState() throws Exception {
        Store store = createStore();
        StoreDTO storeDTO = createStoreDTO();

        when(employeeUseCase.listStoresByState("State1")).thenReturn(Collections.singletonList(store));
        when(storeMapper.toDto(any(Store.class))).thenReturn(storeDTO);

        mockMvc.perform(get("/api/stores/state/State1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].storeName").value("Store1"));
    }

    @Test
    @WithMockUser(authorities = "CUSTOMER")
    void testListByCountry() throws Exception {
        Store store = createStore();
        StoreDTO storeDTO = createStoreDTO();

        when(employeeUseCase.listStoresByCountry("Country1")).thenReturn(Collections.singletonList(store));
        when(storeMapper.toDto(any(Store.class))).thenReturn(storeDTO);

        mockMvc.perform(get("/api/stores/country/Country1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].storeName").value("Store1"));
    }

    @Test
    @WithMockUser(authorities = "CUSTOMER")
    void testListByEmployeeId() throws Exception {
        Store store = createStore();
        StoreDTO storeDTO = createStoreDTO();

        when(employeeUseCase.listStoresByEmployeeId(1L)).thenReturn(Collections.singletonList(store));
        when(storeMapper.toDto(any(Store.class))).thenReturn(storeDTO);

        mockMvc.perform(get("/api/stores/employee/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].storeName").value("Store1"));
    }

    @Test
    @WithMockUser(authorities = "EMPLOYEE")
    void testDelete() throws Exception {
        doNothing().when(employeeUseCase).deleteStore(1L);

        mockMvc.perform(delete("/api/stores/1"))
                .andExpect(status().isOk());
    }
}
