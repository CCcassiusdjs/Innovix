package com.innovix.controller;

import com.innovix.InnovixApplication;
import com.innovix.entity.Address;
import com.innovix.entity.Person;
import com.innovix.entity.Product;
import com.innovix.entity.PurchaseOrder;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = InnovixApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerUseCase customerUseCase;

    @MockBean
    private EmployeeUseCase employeeUseCase;

    private PurchaseOrder mockOrder;

    @BeforeEach
    void setUp() {
        // Create Address instances
        Address addressOrigin = new Address(1L, "Origin Street", 100, "Unit 1", "12345-678", "Origin City", "Origin State", "Origin Country", 1L);
        Address addressDestination = new Address(2L, "Destination Street", 200, "Unit 2", "23456-789", "Destination City", "Destination State", "Destination Country", 2L);

        // Create Product instance
        Product product = new Product(1L, "Sample Product", "Sample Description", "L", "Cotton",
                new Product.Dimensions(), "sample.jpg", 19.99, null, null);

        // Setup mock behaviors
        mockOrder = new PurchaseOrder();
        mockOrder.setId(1L);
        mockOrder.setOrderLocalDate(LocalDate.now());
        mockOrder.setOrderStatus("NEW");
        mockOrder.setCustomer(new Person(1L));
        mockOrder.setAddressOrigin(addressOrigin);
        mockOrder.setAddressDestination(addressDestination);
        mockOrder.setProductName("Sample Product");
        mockOrder.setProductDescription("Sample Description");
        mockOrder.setProductPrice(new BigDecimal("19.99"));
        mockOrder.setProductQuantity(1);
        mockOrder.setProductSubtotal(new BigDecimal("19.99"));
        mockOrder.setFreeQuantity(0);
        mockOrder.setProduct(product);

        Mockito.when(employeeUseCase.listAllOrders()).thenReturn(Collections.singletonList(mockOrder));
        Mockito.when(customerUseCase.createOrder(any())).thenReturn(mockOrder);
        Mockito.when(employeeUseCase.getOrderById(anyLong())).thenReturn(mockOrder);
        Mockito.when(employeeUseCase.listOrdersByStatus(anyString())).thenReturn(Collections.singletonList(mockOrder));
        Mockito.when(customerUseCase.listOrdersByCustomer(any())).thenReturn(Collections.singletonList(mockOrder));
        Mockito.when(employeeUseCase.listOrdersByLocalDateRange(any(), any())).thenReturn(Collections.singletonList(mockOrder));
        Mockito.when(employeeUseCase.listOrdersByProductId(anyLong())).thenReturn(Collections.singletonList(mockOrder));
    }

    @Test
    @WithMockUser(authorities = {"CUSTOMER", "EMPLOYEE"})
    void listAllOrders() throws Exception {
        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].orderStatus").value("NEW"));
    }

    @Test
    @WithMockUser(authorities = "CUSTOMER")
    void saveOrder() throws Exception {
        String orderJson = "{\"orderStatus\":\"NEW\"}";
        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.orderStatus").value("NEW"));
    }

    @Test
    @WithMockUser(authorities = {"CUSTOMER", "EMPLOYEE"})
    void getOrderById() throws Exception {
        mockMvc.perform(get("/api/orders/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.orderStatus").value("NEW"));
    }

    @Test
    @WithMockUser(authorities = "EMPLOYEE")
    void deleteOrder() throws Exception {
        mockMvc.perform(delete("/api/orders/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = {"CUSTOMER", "EMPLOYEE"})
    void listOrdersByStatus() throws Exception {
        mockMvc.perform(get("/api/orders/status/NEW"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].orderStatus").value("NEW"));
    }

    @Test
    @WithMockUser(authorities = "CUSTOMER")
    void listOrdersByCustomer() throws Exception {
        mockMvc.perform(get("/api/orders/customer/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].orderStatus").value("NEW"));
    }

    @Test
    @WithMockUser(authorities = {"CUSTOMER", "EMPLOYEE"})
    void listOrdersByLocalDateRange() throws Exception {
        mockMvc.perform(get("/api/orders/date-range")
                        .param("startLocalDate", "2023-01-01")
                        .param("endLocalDate", "2023-12-31"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].orderStatus").value("NEW"));
    }

    @Test
    @WithMockUser(authorities = {"CUSTOMER", "EMPLOYEE"})
    void listOrdersByProductId() throws Exception {
        mockMvc.perform(get("/api/orders/product/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].orderStatus").value("NEW"));
    }
}
