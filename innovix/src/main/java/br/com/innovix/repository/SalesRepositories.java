package br.com.innovix.repository;

import br.com.innovix.entity.SalesEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public class SalesRepositories {

    @Repository
    public interface SaleRepository extends JpaRepository<SalesEntities.SaleEntity, Long> {
        // Additional query methods can be defined here if needed
    }

    @Repository
    public interface SaleItemsRepository extends JpaRepository<SalesEntities.SaleItemsEntity, Long> {
        // Additional query methods can be defined here if needed
    }
}
