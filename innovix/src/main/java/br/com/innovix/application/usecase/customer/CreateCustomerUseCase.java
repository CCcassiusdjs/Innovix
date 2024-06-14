package br.com.innovix.application.usecase.customer;

import br.com.innovix.domain.entity.Customer;
import br.com.innovix.domain.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class CreateCustomerUseCase {
    private final CustomerRepository customerRepository;

    public CreateCustomerUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer execute(@Valid Customer customer) {
        return customerRepository.save(customer);
    }
}
