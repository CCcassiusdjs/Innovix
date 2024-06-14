package br.com.innovix.application.usecase.category;

import br.com.innovix.domain.entity.Category;
import br.com.innovix.domain.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class CreateCategoryUseCase {
    private final CategoryRepository categoryRepository;

    public CreateCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category execute(@Valid Category category) {
        return categoryRepository.save(category);
    }
}
