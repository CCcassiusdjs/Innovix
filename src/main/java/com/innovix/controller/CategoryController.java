package com.innovix.controller;

import com.innovix.dto.CategoryDTO;
import com.innovix.mapper.CategoryMapper;
import com.innovix.usecase.EmployeeUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for managing categories.
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final EmployeeUseCase employeeUseCase;

    @Autowired
    public CategoryController(EmployeeUseCase employeeUseCase) {
        this.employeeUseCase = employeeUseCase;
    }

    /**
     * Lists all categories.
     *
     * @return A list of all categories.
     */
    @GetMapping
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public List<CategoryDTO> listAll() {
        return employeeUseCase.listAllCategories().stream()
                .map(CategoryMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Saves a new category.
     *
     * @param categoryDTO The category data transfer object.
     * @return The saved category data transfer object.
     */
    @PostMapping
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public CategoryDTO save(@RequestBody CategoryDTO categoryDTO) {
        return CategoryMapper.INSTANCE.toDto(
                employeeUseCase.createCategory(CategoryMapper.INSTANCE.toEntity(categoryDTO))
        );
    }

    /**
     * Gets a category by ID.
     *
     * @param id The ID of the category.
     * @return The category data transfer object.
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public CategoryDTO getById(@PathVariable Long id) {
        return CategoryMapper.INSTANCE.toDto(employeeUseCase.getCategoryById(id));
    }

    /**
     * Deletes a category by ID.
     *
     * @param id The ID of the category to delete.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public void delete(@PathVariable Long id) {
        employeeUseCase.deleteCategory(id);
    }

    /**
     * Gets a category by name.
     *
     * @param name The name of the category.
     * @return The category data transfer object.
     */
    @GetMapping("/name/{name}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public CategoryDTO getByName(@PathVariable String name) {
        return CategoryMapper.INSTANCE.toDto(employeeUseCase.getCategoryByName(name));
    }
}
