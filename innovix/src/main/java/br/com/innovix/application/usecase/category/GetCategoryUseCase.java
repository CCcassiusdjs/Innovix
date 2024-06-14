package br.com.innovix.application.usecase.category;

import br.com.innovix.domain.entity.Category;
import br.com.innovix.domain.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetCategoryUseCase {
    private final CategoryRepository categoryRepository;

    public GetCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<Category> execute(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    public List<Category> execute() {
        return categoryRepository.findAll();
    }
}
