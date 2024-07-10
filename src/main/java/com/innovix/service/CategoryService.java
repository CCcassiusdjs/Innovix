package com.innovix.service;

import com.innovix.entity.Category;
import com.innovix.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing categories.
 * <p>
 * This class provides methods for CRUD operations on Category entities.
 * </p>
 */
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Lists all categories.
     *
     * @return A list of all categories.
     */
    public List<Category> listAll() {
        return categoryRepository.findAll();
    }

    /**
     * Saves a new category or updates an existing one.
     *
     * @param category The category to save.
     * @return The saved category.
     */
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    /**
     * Finds a category by its ID.
     *
     * @param id The ID of the category.
     * @return The category with the specified ID, or null if not found.
     */
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    /**
     * Finds a category by its name.
     *
     * @param name The name of the category.
     * @return The category with the specified name.
     */
    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    /**
     * Deletes a category by its ID.
     *
     * @param id The ID of the category to delete.
     */
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
