package com.innovix.service;

import com.innovix.entity.Category;
import com.innovix.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to manage operations related to {@link Category}.
 */
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * Constructor for dependency injection.
     *
     * @param categoryRepository the category repository.
     */
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Lists all categories.
     *
     * @return a list of categories.
     */
    public List<Category> listAll() {
        return categoryRepository.findAll();
    }

    /**
     * Saves a new category.
     *
     * @param category the category to save.
     * @return the saved category.
     */
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    /**
     * Finds a category by ID.
     *
     * @param id the ID of the category.
     * @return the found category, or {@code null} if not found.
     */
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    /**
     * Finds a category by name.
     *
     * @param name the name of the category.
     * @return the found category.
     */
    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    /**
     * Deletes a category by ID.
     *
     * @param id the ID of the category to delete.
     */
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
