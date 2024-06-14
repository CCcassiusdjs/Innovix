package br.com.innovix.application.usecase.customer;

import br.com.innovix.domain.entity.Customer;
import br.com.innovix.domain.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateCustomerUseCase {
    private final CustomerRepository customerRepository;

    public UpdateCustomerUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> execute(Long customerId, @Valid Customer customer) {
        return customerRepository.findById(customerId).map(existingCustomer -> {
            existingCustomer.setName(customer.getName());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setPhone(customer.getPhone());
            existingCustomer.setAddresses(customer.getAddresses());
            existingCustomer.setPaymentMethods(customer.getPaymentMethods());
            return customerRepository.save(existingCustomer);
        });
    }
}
