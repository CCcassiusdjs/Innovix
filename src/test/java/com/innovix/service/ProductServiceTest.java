package com.innovix.service;

import com.innovix.entity.Product;
import com.innovix.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");
        product1.setPrice(100.0);
        product1.setSize("Medium");

        product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");
        product2.setPrice(50.0);
        product2.setSize("Large");
    }

    @Test
    void testListAll() {
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.listAll();

        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals(product1.getName(), products.get(0).getName());
        assertEquals(product2.getName(), products.get(1).getName());

        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testSave() {
        when(productRepository.save(product1)).thenReturn(product1);

        Product savedProduct = productService.save(product1);

        assertNotNull(savedProduct);
        assertEquals(1L, savedProduct.getId());
        assertEquals("Product 1", savedProduct.getName());

        verify(productRepository, times(1)).save(product1);
    }

    @Test
    void testFindById() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));

        Product foundProduct = productService.findById(1L);

        assertNotNull(foundProduct);
        assertEquals(1L, foundProduct.getId());
        assertEquals("Product 1", foundProduct.getName());

        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByNameContaining() {
        String partialName = "duct";
        when(productRepository.findByNameContaining(partialName)).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.findByNameContaining(partialName);

        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals("Product 1", products.get(0).getName());
        assertEquals("Product 2", products.get(1).getName());

        verify(productRepository, times(1)).findByNameContaining(partialName);
    }

    @Test
    void testFindByCategoryId() {
        Long categoryId = 1L;
        when(productRepository.findByCategoryId(categoryId)).thenReturn(Arrays.asList(product1));

        List<Product> products = productService.findByCategoryId(categoryId);

        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Product 1", products.get(0).getName());

        verify(productRepository, times(1)).findByCategoryId(categoryId);
    }

    @Test
    void testFindByPriceBetween() {
        double minPrice = 50.0;
        double maxPrice = 100.0;
        when(productRepository.findByPriceBetween(minPrice, maxPrice)).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.findByPriceBetween(minPrice, maxPrice);

        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals("Product 1", products.get(0).getName());
        assertEquals("Product 2", products.get(1).getName());

        verify(productRepository, times(1)).findByPriceBetween(minPrice, maxPrice);
    }

    @Test
    void testFindBySize() {
        String size = "Large";
        when(productRepository.findBySize(size)).thenReturn(Arrays.asList(product2));

        List<Product> products = productService.findBySize(size);

        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Product 2", products.get(0).getName());

        verify(productRepository, times(1)).findBySize(size);
    }

    @Test
    void testFindByMaterial() {
        String material = "Cotton"; // Assuming this is a product attribute
        when(productRepository.findByMaterial(material)).thenReturn(Arrays.asList(product1));

        List<Product> products = productService.findByMaterial(material);

        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Product 1", products.get(0).getName());

        verify(productRepository, times(1)).findByMaterial(material);
    }

    @Test
    void testFindByPromotionId() {
        Long promotionId = 1L;
        when(productRepository.findByPromotionId(promotionId)).thenReturn(Arrays.asList(product1));

        List<Product> products = productService.findByPromotionId(promotionId);

        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Product 1", products.get(0).getName());

        verify(productRepository, times(1)).findByPromotionId(promotionId);
    }

    @Test
    void testDelete() {
        doNothing().when(productRepository).deleteById(1L);

        productService.delete(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }
}
