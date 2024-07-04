package com.innovix.repository;

import com.innovix.entity.Category;
import com.innovix.repository.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        Category category1 = new Category(null, "Electronics");
        Category category2 = new Category(null, "Clothing");
        categoryRepository.save(category1);
        categoryRepository.save(category2);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        categoryRepository.deleteAll();
    }

    @org.junit.jupiter.api.Test
    void findByName() {
        Category foundCategory = categoryRepository.findByName("Electronics");
        assertNotNull(foundCategory);
        assertEquals("Electronics", foundCategory.getName());
    }

    @org.junit.jupiter.api.Test
    void findByName_NotFound() {
        Category foundCategory = categoryRepository.findByName("Books");
        assertNull(foundCategory);
    }
}
