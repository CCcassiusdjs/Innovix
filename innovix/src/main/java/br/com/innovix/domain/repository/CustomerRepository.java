package br.com.innovix.domain.repository;

import br.com.innovix.domain.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Customer save(Customer customer);
    Optional<Customer> findById(Long customerId);
    List<Customer> findAll();
    void deleteById(Long customerId);
}
