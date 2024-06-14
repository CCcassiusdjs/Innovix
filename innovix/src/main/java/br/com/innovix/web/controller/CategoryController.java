package br.com.innovix.web.controller;

import br.com.innovix.application.usecase.category.CreateCategoryUseCase;
import br.com.innovix.application.usecase.category.DeleteCategoryUseCase;
import br.com.innovix.application.usecase.category.GetCategoryUseCase;
import br.com.innovix.application.usecase.category.UpdateCategoryUseCase;
import br.com.innovix.domain.entity.Category;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CreateCategoryUseCase createCategoryUseCase;

    @Autowired
    private GetCategoryUseCase getCategoryUseCase;

    @Autowired
    private UpdateCategoryUseCase updateCategoryUseCase;

    @Autowired
    private DeleteCategoryUseCase deleteCategoryUseCase;

    @PostMapping
    public Category createCategory(@RequestBody @Valid Category category) {
        return createCategoryUseCase.execute(category);
    }

    @GetMapping("/{id}")
    public Optional<Category> getCategory(@PathVariable Long id) {
        return getCategoryUseCase.execute(id);
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return getCategoryUseCase.execute();
    }

    @PutMapping("/{id}")
    public Optional<Category> updateCategory(@PathVariable Long id, @RequestBody @Valid Category category) {
        return updateCategoryUseCase.execute(id, category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        deleteCategoryUseCase.execute(id);
    }
}
