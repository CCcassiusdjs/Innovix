package com.innovix.repository;

import com.innovix.entity.Category;
import com.innovix.entity.Product;
import com.innovix.entity.Promotion;
import com.innovix.repository.CategoryRepository;
import com.innovix.repository.ProductRepository;
import com.innovix.repository.PromotionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    private Category category;
    private Promotion promotion;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        category = new Category();
        category.setName("Electronics");
        category = categoryRepository.save(category);

        promotion = new Promotion();
        promotion.setDescription("Summer Sale");
        promotion.setSeason("Summer");
        promotion = promotionRepository.save(promotion);
    }

    @org.junit.jupiter.api.Test
    void tearDown() {
        productRepository.deleteAll();
        categoryRepository.deleteAll();
        promotionRepository.deleteAll();
    }

    @org.junit.jupiter.api.Test
    void findByNameContaining() {
        Product product = createSampleProduct();
        product.setName("Product ABC");
        productRepository.save(product);

        List<Product> foundProducts = productRepository.findByNameContaining("ABC");
        assertNotNull(foundProducts);
        assertEquals(1, foundProducts.size());
        assertEquals("Product ABC", foundProducts.get(0).getName());
    }

    @org.junit.jupiter.api.Test
    void findByCategoryId() {
        Product product = createSampleProduct();
        product.setCategory(category);
        productRepository.save(product);

        List<Product> foundProducts = productRepository.findByCategoryId(category.getId());
        assertNotNull(foundProducts);
        assertEquals(1, foundProducts.size());
        assertEquals(category.getId(), foundProducts.get(0).getCategory().getId());
    }

    @org.junit.jupiter.api.Test
    void findByPriceBetween() {
        Product product = createSampleProduct();
        product.setPrice(50.0);
        productRepository.save(product);

        List<Product> foundProducts = productRepository.findByPriceBetween(40.0, 60.0);
        assertNotNull(foundProducts);
        assertEquals(1, foundProducts.size());
        assertEquals(product.getId(), foundProducts.get(0).getId());
    }

    @org.junit.jupiter.api.Test
    void findBySize() {
        Product product = createSampleProduct();
        product.setSize("Large");
        productRepository.save(product);

        List<Product> foundProducts = productRepository.findBySize("Large");
        assertNotNull(foundProducts);
        assertEquals(1, foundProducts.size());
        assertEquals("Large", foundProducts.get(0).getSize());
    }

    @org.junit.jupiter.api.Test
    void findByMaterial() {
        Product product = createSampleProduct();
        product.setMaterial("Cotton");
        productRepository.save(product);

        List<Product> foundProducts = productRepository.findByMaterial("Cotton");
        assertNotNull(foundProducts);
        assertEquals(1, foundProducts.size());
        assertEquals("Cotton", foundProducts.get(0).getMaterial());
    }

    @org.junit.jupiter.api.Test
    void findByPromotionId() {
        Product product = createSampleProduct();
        product.setPromotion(promotion);
        productRepository.save(product);

        List<Product> foundProducts = productRepository.findByPromotionId(promotion.getId());
        assertNotNull(foundProducts);
        assertEquals(1, foundProducts.size());
        assertEquals(promotion.getId(), foundProducts.get(0).getPromotion().getId());
    }

    private Product createSampleProduct() {
        Product product = new Product();
        product.setName("Sample Product");
        product.setDescription("Sample Description");
        product.setPrice(100.0);
        product.setCategory(new Category());
        product.setPromotion(new Promotion());
        return product;
    }
}
