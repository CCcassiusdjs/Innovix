package com.innovix.controller;

import com.innovix.dto.ShoppingCartDTO;
import com.innovix.entity.Person;
import com.innovix.entity.ShoppingCart;
import com.innovix.mapper.ShoppingCartMapper;
import com.innovix.usecase.CustomerUseCase;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ShoppingCartControllerTest {

    @Mock
    private CustomerUseCase customerUseCase;

    @Mock
    private ShoppingCartMapper shoppingCartMapper;

    @InjectMocks
    private ShoppingCartController shoppingCartController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(shoppingCartController).build();
    }

    private ShoppingCart createShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);
        // Add more fields as necessary
        return shoppingCart;
    }

    private ShoppingCartDTO createShoppingCartDTO() {
        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
        shoppingCartDTO.setShoppingCartId(1L);
        // Add more fields as necessary
        return shoppingCartDTO;
    }

    @Test
    @WithMockUser(authorities = "CUSTOMER")
    void listAll() throws Exception {
        ShoppingCart shoppingCart = createShoppingCart();
        ShoppingCartDTO shoppingCartDTO = createShoppingCartDTO();

        when(customerUseCase.listAllShoppingCarts()).thenReturn(Collections.singletonList(shoppingCart));
        when(shoppingCartMapper.toDto(any(ShoppingCart.class))).thenReturn(shoppingCartDTO);

        mockMvc.perform(get("/api/shopping-carts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].shoppingCartId").value(1L));
    }

    @Test
    @WithMockUser(authorities = "CUSTOMER")
    void save() throws Exception {
        ShoppingCart shoppingCart = createShoppingCart();
        ShoppingCartDTO shoppingCartDTO = createShoppingCartDTO();

        when(shoppingCartMapper.toEntity(any(ShoppingCartDTO.class))).thenReturn(shoppingCart);
        when(customerUseCase.createShoppingCart(any(ShoppingCart.class))).thenReturn(shoppingCart);
        when(shoppingCartMapper.toDto(any(ShoppingCart.class))).thenReturn(shoppingCartDTO);

        mockMvc.perform(post("/api/shopping-carts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"shoppingCartId\": 1}")) // Add more fields as necessary
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.shoppingCartId").value(1L));
    }

    @Test
    @WithMockUser(authorities = "CUSTOMER")
    void getById() throws Exception {
        ShoppingCart shoppingCart = createShoppingCart();
        ShoppingCartDTO shoppingCartDTO = createShoppingCartDTO();

        when(customerUseCase.getShoppingCartById(1L)).thenReturn(shoppingCart);
        when(shoppingCartMapper.toDto(any(ShoppingCart.class))).thenReturn(shoppingCartDTO);

        mockMvc.perform(get("/api/shopping-carts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.shoppingCartId").value(1L));
    }

    @Test
    @WithMockUser(authorities = "CUSTOMER")
    void listByCustomer() throws Exception {
        ShoppingCart shoppingCart = createShoppingCart();
        ShoppingCartDTO shoppingCartDTO = createShoppingCartDTO();

        when(customerUseCase.listShoppingCartsByCustomer(any(Person.class))).thenReturn(Collections.singletonList(shoppingCart));
        when(shoppingCartMapper.toDto(any(ShoppingCart.class))).thenReturn(shoppingCartDTO);

        mockMvc.perform(get("/api/shopping-carts/customer/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].shoppingCartId").value(1L));
    }

    @Test
    @WithMockUser(authorities = "CUSTOMER")
    void deleteShoppingCart() throws Exception {
        doNothing().when(customerUseCase).deleteShoppingCart(1L);

        mockMvc.perform(delete("/api/shopping-carts/1"))
                .andExpect(status().isOk());
    }
}
