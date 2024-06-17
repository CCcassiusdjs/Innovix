package com.innovix.usecase;

import com.innovix.entity.Category;
import com.innovix.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Use case for managing operations related to {@link Category}.
 */
@Component
public class CategoryUseCase {

    private final CategoryService categoryService;

    /**
     * Constructor for dependency injection.
     *
     * @param categoryService the category service.
     */
    @Autowired
    public CategoryUseCase(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Lists all categories.
     *
     * @return a list of categories.
     */
    public List<Category> listAllCategories() {
        return categoryService.listAll();
    }

    /**
     * Creates a new category.
     *
     * @param category the category to create.
     * @return the created category.
     */
    public Category createCategory(Category category) {
        return categoryService.save(category);
    }

    /**
     * Gets a category by ID.
     *
     * @param id the ID of the category.
     * @return the found category, or {@code null} if not found.
     */
    public Category getCategoryById(Long id) {
        return categoryService.findById(id);
    }

    /**
     * Gets a category by name.
     *
     * @param name the name of the category.
     * @return the found category.
     */
    public Category getCategoryByName(String name) {
        return categoryService.findByName(name);
    }

    /**
     * Deletes a category by ID.
     *
     * @param id the ID of the category to delete.
     */
    public void deleteCategory(Long id) {
        categoryService.delete(id);
    }
}
