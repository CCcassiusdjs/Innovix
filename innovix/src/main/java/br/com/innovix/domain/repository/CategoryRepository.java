package br.com.innovix.domain.repository;

import br.com.innovix.domain.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Category save(Category category);
    Optional<Category> findById(Long categoryId);
    List<Category> findAll();
    void deleteById(Long categoryId);
}
