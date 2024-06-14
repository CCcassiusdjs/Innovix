package br.com.innovix.application.usecase.category;

import br.com.innovix.domain.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteCategoryUseCase {
    private final CategoryRepository categoryRepository;

    public DeleteCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void execute(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
