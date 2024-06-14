package br.com.innovix.frameworks.repository.jpa;

import br.com.innovix.domain.entity.Product;
import br.com.innovix.domain.repository.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductRepository extends JpaRepository<Product, Long>, ProductRepository {
}
