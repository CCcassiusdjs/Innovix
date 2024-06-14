package br.com.innovix.application.usecase.category;

import br.com.innovix.domain.entity.Category;
import br.com.innovix.domain.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UpdateCategoryUseCase {
    private final CategoryRepository categoryRepository;

    public UpdateCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<Category> execute(Long categoryId, @Valid Category category) {
        return categoryRepository.findById(categoryId).map(existingCategory -> {
            existingCategory.setName(category.getName());
            return categoryRepository.save(existingCategory);
        });
    }
}
