package br.com.innovix.frameworks.repository.jpa;

import br.com.innovix.domain.entity.Category;
import br.com.innovix.domain.repository.CategoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCategoryRepository extends JpaRepository<Category, Long>, CategoryRepository {
}
