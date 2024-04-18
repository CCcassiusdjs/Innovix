package br.com.innovix.repository.product;

import br.com.innovix.entity.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    // Additional query methods can be defined here if needed
}