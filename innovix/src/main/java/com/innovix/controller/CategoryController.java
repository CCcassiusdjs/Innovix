package com.innovix.controller;

import com.innovix.dto.CategoryDTO;
import com.innovix.mapper.CategoryMapper;
import com.innovix.usecase.CategoryUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryUseCase categoryUseCase;

    @Autowired
    public CategoryController(CategoryUseCase categoryUseCase) {
        this.categoryUseCase = categoryUseCase;
    }

    @GetMapping
    public List<CategoryDTO> listAll() {
        return categoryUseCase.listAllCategories().stream()
                .map(CategoryMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public CategoryDTO save(@RequestBody CategoryDTO categoryDTO) {
        return CategoryMapper.INSTANCE.toDto(
                categoryUseCase.createCategory(CategoryMapper.INSTANCE.toEntity(categoryDTO))
        );
    }

    @GetMapping("/{id}")
    public CategoryDTO getById(@PathVariable Long id) {
        return CategoryMapper.INSTANCE.toDto(categoryUseCase.getCategoryById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryUseCase.deleteCategory(id);
    }

    @GetMapping("/{name}")
    public CategoryDTO getByName(@PathVariable String name) {
        return CategoryMapper.INSTANCE.toDto(categoryUseCase.getCategoryByName(name));
    }
}
