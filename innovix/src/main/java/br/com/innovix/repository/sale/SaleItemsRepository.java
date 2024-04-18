package br.com.innovix.repository.sale;

import br.com.innovix.entity.sale.SaleItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleItemsRepository extends JpaRepository<SaleItemsEntity, Long> {
    // Additional query methods can be defined here if needed
}