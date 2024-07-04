package com.innovix.mapper;

import com.innovix.dto.CategoryDTO;
import com.innovix.entity.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    private final CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    void toDto() {
        // Arrange
        Category category = new Category();
        category.setId(1L);
        category.setName("Test Category");

        // Act
        CategoryDTO categoryDTO = categoryMapper.toDto(category);

        // Assert
        assertNotNull(categoryDTO);
        assertEquals(1L, categoryDTO.getCategoryId());
        assertEquals("Test Category", categoryDTO.getName());
    }

    @Test
    void toEntity() {
        // Arrange
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(1L);
        categoryDTO.setName("Test Category");

        // Act
        Category category = categoryMapper.toEntity(categoryDTO);

        // Assert
        assertNotNull(category);
        assertEquals(1L, category.getId());
        assertEquals("Test Category", category.getName());
    }
}
