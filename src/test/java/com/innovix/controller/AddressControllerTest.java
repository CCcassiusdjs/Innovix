package com.innovix.controller;

import com.innovix.dto.AddressDTO;
import com.innovix.entity.Address;
import com.innovix.mapper.AddressMapper;
import com.innovix.usecase.CustomerUseCase;
import com.innovix.usecase.EmployeeUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeUseCase employeeUseCase;

    @MockBean
    private CustomerUseCase customerUseCase;

    private AddressDTO addressDTO;

    @BeforeEach
    void setUp() {
        addressDTO = new AddressDTO();
        addressDTO.setAddressId(1L);
        addressDTO.setStreetName("123 Main St");
        addressDTO.setNumber(101);
        addressDTO.setUnit("Apt 1B");
        addressDTO.setZipCode("12345-678");
        addressDTO.setCity("Springfield");
        addressDTO.setState("IL");
        addressDTO.setCountry("USA");
        addressDTO.setPersonId(1L);

        Address address = AddressMapper.INSTANCE.toEntity(addressDTO);

        Mockito.when(employeeUseCase.listAllAddresses())
                .thenReturn(Collections.singletonList(address));

        Mockito.when(employeeUseCase.createAddress(any()))
                .thenReturn(address);

        Mockito.when(employeeUseCase.getAddressById(anyLong()))
                .thenReturn(address);

        Mockito.when(employeeUseCase.listAddressesByCity(anyString()))
                .thenReturn(Collections.singletonList(address));

        Mockito.when(employeeUseCase.listAddressesByState(anyString()))
                .thenReturn(Collections.singletonList(address));

        Mockito.when(employeeUseCase.listAddressesByCountry(anyString()))
                .thenReturn(Collections.singletonList(address));

        Mockito.when(employeeUseCase.listAddressesByPersonId(anyLong()))
                .thenReturn(Collections.singletonList(address));
    }

    @Test
    @WithMockUser(authorities = {"CUSTOMER", "EMPLOYEE"})
    void listAll() throws Exception {
        mockMvc.perform(get("/api/addresses"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].streetName").value("123 Main St"));
    }

    @Test
    @WithMockUser(authorities = "EMPLOYEE")
    void save() throws Exception {
        String addressJson = "{\"addressId\":1,\"streetName\":\"123 Main St\",\"number\":101,\"unit\":\"Apt 1B\",\"zipCode\":\"12345-678\",\"city\":\"Springfield\",\"state\":\"IL\",\"country\":\"USA\",\"personId\":1}";

        mockMvc.perform(post("/api/addresses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(addressJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.streetName").value("123 Main St"));
    }

    @Test
    @WithMockUser(authorities = {"CUSTOMER", "EMPLOYEE"})
    void getById() throws Exception {
        mockMvc.perform(get("/api/addresses/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.streetName").value("123 Main St"));
    }

    @Test
    @WithMockUser(authorities = {"CUSTOMER", "EMPLOYEE"})
    void listByCity() throws Exception {
        mockMvc.perform(get("/api/addresses/city/Springfield"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].streetName").value("123 Main St"));
    }

    @Test
    @WithMockUser(authorities = {"CUSTOMER", "EMPLOYEE"})
    void listByState() throws Exception {
        mockMvc.perform(get("/api/addresses/state/IL"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].streetName").value("123 Main St"));
    }

    @Test
    @WithMockUser(authorities = {"CUSTOMER", "EMPLOYEE"})
    void listByCountry() throws Exception {
        mockMvc.perform(get("/api/addresses/country/USA"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].streetName").value("123 Main St"));
    }

    @Test
    @WithMockUser(authorities = {"CUSTOMER", "EMPLOYEE"})
    void listByPersonId() throws Exception {
        mockMvc.perform(get("/api/addresses/person/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].streetName").value("123 Main St"));
    }

    @Test
    @WithMockUser(authorities = "EMPLOYEE")
    void deleteAddress() throws Exception {
        mockMvc.perform(delete("/api/addresses/1"))
                .andExpect(status().isOk());
    }
}
