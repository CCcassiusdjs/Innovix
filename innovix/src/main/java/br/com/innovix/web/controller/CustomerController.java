package br.com.innovix.web.controller;

import br.com.innovix.application.usecase.customer.CreateCustomerUseCase;
import br.com.innovix.application.usecase.customer.DeleteCustomerUseCase;
import br.com.innovix.application.usecase.customer.GetCustomerUseCase;
import br.com.innovix.application.usecase.customer.UpdateCustomerUseCase;
import br.com.innovix.domain.entity.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CreateCustomerUseCase createCustomerUseCase;

    @Autowired
    private GetCustomerUseCase getCustomerUseCase;

    @Autowired
    private UpdateCustomerUseCase updateCustomerUseCase;

    @Autowired
    private DeleteCustomerUseCase deleteCustomerUseCase;

    @PostMapping
    public Customer createCustomer(@RequestBody @Valid Customer customer) {
        return createCustomerUseCase.execute(customer);
    }

    @GetMapping("/{id}")
    public Optional<Customer> getCustomer(@PathVariable Long id) {
        return getCustomerUseCase.execute(id);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return getCustomerUseCase.execute();
    }

    @PutMapping("/{id}")
    public Optional<Customer> updateCustomer(@PathVariable Long id, @RequestBody @Valid Customer customer) {
        return updateCustomerUseCase.execute(id, customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        deleteCustomerUseCase.execute(id);
    }
}
