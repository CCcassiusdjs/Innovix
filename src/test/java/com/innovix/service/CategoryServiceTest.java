package com.innovix.service;

import com.innovix.entity.Category;
import com.innovix.repository.CategoryRepository;
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
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private Category category1;
    private Category category2;

    @BeforeEach
    void setUp() {
        category1 = new Category();
        category1.setId(1L);
        category1.setName("Category1");

        category2 = new Category();
        category2.setId(2L);
        category2.setName("Category2");
    }

    @Test
    void testListAll() {
        when(categoryRepository.findAll()).thenReturn(Arrays.asList(category1, category2));

        List<Category> categories = categoryService.listAll();

        assertNotNull(categories);
        assertEquals(2, categories.size());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void testSave() {
        when(categoryRepository.save(category1)).thenReturn(category1);

        Category savedCategory = categoryService.save(category1);

        assertNotNull(savedCategory);
        assertEquals(1L, savedCategory.getId());
        assertEquals("Category1", savedCategory.getName());
        verify(categoryRepository, times(1)).save(category1);
    }

    @Test
    void testFindById() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category1));

        Category foundCategory = categoryService.findById(1L);

        assertNotNull(foundCategory);
        assertEquals(1L, foundCategory.getId());
        verify(categoryRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByName() {
        when(categoryRepository.findByName("Category1")).thenReturn(category1);

        Category foundCategory = categoryService.findByName("Category1");

        assertNotNull(foundCategory);
        assertEquals("Category1", foundCategory.getName());
        verify(categoryRepository, times(1)).findByName("Category1");
    }

    @Test
    void testDelete() {
        doNothing().when(categoryRepository).deleteById(1L);

        categoryService.delete(1L);

        verify(categoryRepository, times(1)).deleteById(1L);
    }
}
