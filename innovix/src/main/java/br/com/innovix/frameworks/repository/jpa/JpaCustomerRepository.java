package br.com.innovix.frameworks.repository.jpa;

import br.com.innovix.domain.entity.Customer;
import br.com.innovix.domain.repository.CustomerRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCustomerRepository extends JpaRepository<Customer, Long>, CustomerRepository {
}
