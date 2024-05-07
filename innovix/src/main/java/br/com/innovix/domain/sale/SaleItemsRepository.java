package br.com.innovix.domain.sale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleItemsRepository extends JpaRepository<SaleItemsEntity, Long> {
    // Additional query methods can be defined here if needed
}