package com.innovix.controller;

import com.innovix.dto.AddressDTO;
import com.innovix.dto.OrderDTO;
import com.innovix.dto.PersonDTO;
import com.innovix.entity.Person;
import com.innovix.mapper.AddressMapper;
import com.innovix.mapper.OrderMapper;
import com.innovix.usecase.CustomerUseCase;
import com.innovix.usecase.EmployeeUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasSize;

class PersonControllerTest {

    @Mock
    private CustomerUseCase customerUseCase;

    @Mock
    private EmployeeUseCase employeeUseCase;

    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private PersonController personController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }

    @Test
    @WithMockUser(authorities = "EMPLOYEE")
    void listAll() throws Exception {
        when(employeeUseCase.listAllEmployees()).thenReturn(Collections.singletonList(new PersonDTO()));

        mockMvc.perform(get("/api/persons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @WithMockUser(authorities = "EMPLOYEE")
    void listAllCustomers() throws Exception {
        when(customerUseCase.listAllCustomers()).thenReturn(Collections.singletonList(new PersonDTO()));

        mockMvc.perform(get("/api/persons/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @WithMockUser
    void createAddress() throws Exception {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setPersonId(1L);

        when(userDetails.getUsername()).thenReturn("test@example.com");
        PersonDTO customerDTO = new PersonDTO();
        customerDTO.setId(1L);
        customerDTO.setType("CUSTOMER");
        when(customerUseCase.getCustomerByEmail(any())).thenReturn(customerDTO);
        when(employeeUseCase.createAddress(any())).thenReturn(AddressMapper.INSTANCE.toEntity(addressDTO));

        SecurityContextHolder.setContext(new SecurityContextImpl());
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null));

        mockMvc.perform(post("/api/persons/1/addresses")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"street\":\"Test Street\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.personId").value(1L));
    }

    @Test
    @WithMockUser
    void listOrdersByCustomer() throws Exception {
        when(userDetails.getUsername()).thenReturn("test@example.com");
        PersonDTO customerDTO = new PersonDTO();
        customerDTO.setId(1L);
        customerDTO.setType("CUSTOMER");
        when(customerUseCase.getCustomerByEmail(any())).thenReturn(customerDTO);
        when(customerUseCase.listOrdersByCustomer(any())).thenReturn(Collections.singletonList(OrderMapper.INSTANCE.toEntity(new OrderDTO())));

        SecurityContextHolder.setContext(new SecurityContextImpl());
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null));

        mockMvc.perform(get("/api/persons/customer-orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @WithMockUser(authorities = "EMPLOYEE")
    void getById() throws Exception {
        PersonDTO employeeDTO = new PersonDTO();
        employeeDTO.setId(1L);
        employeeDTO.setType("EMPLOYEE");
        when(employeeUseCase.getEmployeeById(1L)).thenReturn(employeeDTO);

        mockMvc.perform(get("/api/persons/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    @WithMockUser(authorities = "EMPLOYEE")
    void deleteEmployee() throws Exception {
        when(userDetails.getUsername()).thenReturn("test@example.com");
        PersonDTO loggedInUser = new PersonDTO();
        loggedInUser.setId(2L);
        loggedInUser.setType("EMPLOYEE");
        when(employeeUseCase.getEmployeeByEmail(any())).thenReturn(loggedInUser);

        SecurityContextHolder.setContext(new SecurityContextImpl());
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null));

        mockMvc.perform(delete("/api/persons/employee/1")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser
    void deleteCustomer() throws Exception {
        when(userDetails.getUsername()).thenReturn("test@example.com");
        PersonDTO loggedInUser = new PersonDTO();
        loggedInUser.setId(1L);
        loggedInUser.setType("CUSTOMER");
        when(customerUseCase.getCustomerByEmail(any())).thenReturn(loggedInUser);

        SecurityContextHolder.setContext(new SecurityContextImpl());
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null));

        mockMvc.perform(delete("/api/persons/customer/1")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isNoContent());
    }

    @Test
    void registerCustomer() throws Exception {
        when(customerUseCase.userExists(any())).thenReturn(false);

        mockMvc.perform(post("/api/persons/registerCustomer")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Customer registered successfully"));
    }

    @Test
    @WithMockUser(authorities = "EMPLOYEE")
    void registerEmployee() throws Exception {
        when(employeeUseCase.userExists(any())).thenReturn(false);

        mockMvc.perform(post("/api/persons/registerEmployee")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Employee registered successfully"));
    }
}
