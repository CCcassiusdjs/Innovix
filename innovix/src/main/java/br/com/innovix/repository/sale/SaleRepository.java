package br.com.innovix.repository.sale;

import br.com.innovix.entity.sale.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<SaleEntity, Long> {
    // Additional query methods can be defined here if needed
}