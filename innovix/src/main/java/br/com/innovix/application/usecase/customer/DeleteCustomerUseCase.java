package br.com.innovix.application.usecase.customer;

import br.com.innovix.domain.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteCustomerUseCase {
    private final CustomerRepository customerRepository;

    public DeleteCustomerUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void execute(Long customerId) {
        customerRepository.deleteById(customerId);
    }
}
