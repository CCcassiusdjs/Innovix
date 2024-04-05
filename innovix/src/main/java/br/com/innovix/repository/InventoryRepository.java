package br.com.innovix.repository;

import br.com.innovix.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {

}
