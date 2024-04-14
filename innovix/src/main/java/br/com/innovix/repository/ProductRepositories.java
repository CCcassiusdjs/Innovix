package br.com.innovix.repository;

import br.com.innovix.entity.ProductEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public class ProductRepositories {

    @Repository
    public interface InventoryRepository extends JpaRepository<ProductEntities.InventoryEntity, Long> {
        // Additional query methods can be defined here if needed
    }

    @Repository
    public interface ProductRepository extends JpaRepository<ProductEntities.ProductEntity, Long> {
        // Additional query methods can be defined here if needed
    }
}
