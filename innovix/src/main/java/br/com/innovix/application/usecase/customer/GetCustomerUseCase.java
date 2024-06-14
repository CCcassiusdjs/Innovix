package br.com.innovix.application.usecase.customer;

import br.com.innovix.domain.entity.Customer;
import br.com.innovix.domain.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetCustomerUseCase {
    private final CustomerRepository customerRepository;

    public GetCustomerUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> execute(Long customerId) {
        return customerRepository.findById(customerId);
    }

    public List<Customer> execute() {
        return customerRepository.findAll();
    }
}
