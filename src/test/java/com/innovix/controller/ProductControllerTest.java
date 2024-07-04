package com.innovix.controller;

import com.innovix.dto.ProductDTO;
import com.innovix.entity.Product;
import com.innovix.mapper.ProductMapper;
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

class ProductControllerTest {

    @Mock
    private EmployeeUseCase employeeUseCase;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    private Product createProduct() {
        Product product = new Product();
        product.setId(1L);
        // Add more fields as necessary
        return product;
    }

    private ProductDTO createProductDTO() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(1L);
        // Add more fields as necessary
        return productDTO;
    }

    @Test
    @WithMockUser(authorities = {"CUSTOMER", "EMPLOYEE"})
    void listAll() throws Exception {
        Product product = createProduct();
        ProductDTO productDTO = createProductDTO();

        when(employeeUseCase.listAllProducts()).thenReturn(Collections.singletonList(product));
        when(productMapper.toDto(any(Product.class))).thenReturn(productDTO);

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].productId").value(1L));
    }

    @Test
    @WithMockUser(authorities = "EMPLOYEE")
    void save() throws Exception {
        Product product = createProduct();
        ProductDTO productDTO = createProductDTO();

        when(productMapper.toEntity(any(ProductDTO.class))).thenReturn(product);
        when(employeeUseCase.createProduct(any(Product.class))).thenReturn(product);
        when(productMapper.toDto(any(Product.class))).thenReturn(productDTO);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"productId\": 1}")) // Add more fields as necessary
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(1L));
    }

    @Test
    @WithMockUser(authorities = {"CUSTOMER", "EMPLOYEE"})
    void getById() throws Exception {
        Product product = createProduct();
        ProductDTO productDTO = createProductDTO();

        when(employeeUseCase.getProductById(1L)).thenReturn(product);
        when(productMapper.toDto(any(Product.class))).thenReturn(productDTO);

        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(1L));
    }

    @Test
    @WithMockUser(authorities = {"CUSTOMER", "EMPLOYEE"})
    void listByNameContaining() throws Exception {
        Product product = createProduct();
        ProductDTO productDTO = createProductDTO();

        when(employeeUseCase.listProductsByNameContaining("Sample")).thenReturn(Collections.singletonList(product));
        when(productMapper.toDto(any(Product.class))).thenReturn(productDTO);

        mockMvc.perform(get("/api/products/name/Sample"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].productId").value(1L));
    }

    @Test
    @WithMockUser(authorities = {"CUSTOMER", "EMPLOYEE"})
    void listByCategoryId() throws Exception {
        Product product = createProduct();
        ProductDTO productDTO = createProductDTO();

        when(employeeUseCase.listProductsByCategoryId(1L)).thenReturn(Collections.singletonList(product));
        when(productMapper.toDto(any(Product.class))).thenReturn(productDTO);

        mockMvc.perform(get("/api/products/category/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].productId").value(1L));
    }

    @Test
    @WithMockUser(authorities = {"CUSTOMER", "EMPLOYEE"})
    void listByPriceRange() throws Exception {
        Product product = createProduct();
        ProductDTO productDTO = createProductDTO();

        when(employeeUseCase.listProductsByPriceBetween(10.0, 20.0)).thenReturn(Collections.singletonList(product));
        when(productMapper.toDto(any(Product.class))).thenReturn(productDTO);

        mockMvc.perform(get("/api/products/price-range")
                        .param("minPrice", "10.0")
                        .param("maxPrice", "20.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].productId").value(1L));
    }

    @Test
    @WithMockUser(authorities = {"CUSTOMER", "EMPLOYEE"})
    void listBySize() throws Exception {
        Product product = createProduct();
        ProductDTO productDTO = createProductDTO();

        when(employeeUseCase.listProductsBySize("M")).thenReturn(Collections.singletonList(product));
        when(productMapper.toDto(any(Product.class))).thenReturn(productDTO);

        mockMvc.perform(get("/api/products/size/M"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].productId").value(1L));
    }

    @Test
    @WithMockUser(authorities = {"CUSTOMER", "EMPLOYEE"})
    void listByMaterial() throws Exception {
        Product product = createProduct();
        ProductDTO productDTO = createProductDTO();

        when(employeeUseCase.listProductsByMaterial("Cotton")).thenReturn(Collections.singletonList(product));
        when(productMapper.toDto(any(Product.class))).thenReturn(productDTO);

        mockMvc.perform(get("/api/products/material/Cotton"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].productId").value(1L));
    }

    @Test
    @WithMockUser(authorities = {"CUSTOMER", "EMPLOYEE"})
    void listByPromotionId() throws Exception {
        Product product = createProduct();
        ProductDTO productDTO = createProductDTO();

        when(employeeUseCase.listProductsByPromotionId(1L)).thenReturn(Collections.singletonList(product));
        when(productMapper.toDto(any(Product.class))).thenReturn(productDTO);

        mockMvc.perform(get("/api/products/promotion/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].productId").value(1L));
    }

    @Test
    @WithMockUser(authorities = "EMPLOYEE")
    void deleteProduct() throws Exception {
        doNothing().when(employeeUseCase).deleteProduct(1L);

        mockMvc.perform(delete("/api/products/1"))
                .andExpect(status().isOk());
    }
}
