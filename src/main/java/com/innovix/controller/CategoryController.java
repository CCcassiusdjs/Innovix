package com.innovix.controller;

import com.innovix.dto.CategoryDTO;
import com.innovix.mapper.CategoryMapper;
import com.innovix.usecase.EmployeeUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final EmployeeUseCase employeeUseCase;

    @Autowired
    public CategoryController(EmployeeUseCase employeeUseCase) {
        this.employeeUseCase = employeeUseCase;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public List<CategoryDTO> listAll() {
        return employeeUseCase.listAllCategories().stream()
                .map(CategoryMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public CategoryDTO save(@RequestBody CategoryDTO categoryDTO) {
        return CategoryMapper.INSTANCE.toDto(
                employeeUseCase.createCategory(CategoryMapper.INSTANCE.toEntity(categoryDTO))
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public CategoryDTO getById(@PathVariable Long id) {
        return CategoryMapper.INSTANCE.toDto(employeeUseCase.getCategoryById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public void delete(@PathVariable Long id) {
        employeeUseCase.deleteCategory(id);
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public CategoryDTO getByName(@PathVariable String name) {
        return CategoryMapper.INSTANCE.toDto(employeeUseCase.getCategoryByName(name));
    }
}
