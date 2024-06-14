package br.com.innovix.domain.repository;

import br.com.innovix.domain.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(Long productId);
    List<Product> findAll();
    void deleteById(Long productId);
}
