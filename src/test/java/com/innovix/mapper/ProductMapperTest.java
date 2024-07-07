package com.innovix.mapper;

import com.innovix.dto.ProductDTO;
import com.innovix.entity.Product;
import com.innovix.entity.Category;
import com.innovix.entity.Promotion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    @Test
    void toDto() {
        // Arrange
        Product product = new Product();
        product.setId(1L);
        Category category = new Category();
        category.setId(2L);
        Promotion promotion = new Promotion();
        promotion.setId(3L);
        product.setCategory(category);
        product.setPromotion(promotion);

        // Act
        ProductDTO productDTO = productMapper.toDto(product);

        // Assert
        assertNotNull(productDTO);
        assertEquals(1L, productDTO.getProductId());
        assertEquals(2L, productDTO.getCategoryId());
        assertEquals(3L, productDTO.getPromotionId());
    }

    @Test
    void toEntity() {
        // Arrange
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(1L);
        productDTO.setCategoryId(2L);
        productDTO.setPromotionId(3L);

        // Act
        Product product = productMapper.toEntity(productDTO);

        // Assert
        assertNotNull(product);
        assertEquals(1L, product.getId());
        assertNotNull(product.getCategory());
        assertEquals(2L, product.getCategory().getId());
        assertNotNull(product.getPromotion());
        assertEquals(3L, product.getPromotion().getId());
    }
}
