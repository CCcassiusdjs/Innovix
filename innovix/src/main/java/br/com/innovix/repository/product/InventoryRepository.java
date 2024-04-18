package br.com.innovix.repository.product;

import br.com.innovix.entity.product.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {
    // Additional query methods can be defined here if needed
}