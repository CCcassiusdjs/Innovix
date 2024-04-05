package br.com.innovix.controller;

import br.com.innovix.dto.CustomerDTO;
import br.com.innovix.entity.CustomerEntity;
import br.com.innovix.exception.CustomerNotFoundException;
import br.com.innovix.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(CustomerDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable Long id) {
        CustomerEntity customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Cliente não encontrado com o ID: " + id));
        return CustomerDTO.fromEntity(customer);
    }

    @PostMapping
    public CustomerDTO addCustomer(@RequestBody CustomerDTO customerDTO) {
        customerDTO.validateForCreationOrUpdate();
        CustomerEntity customer = new CustomerEntity();
        // Preencher a entidade com base nos dados do DTO
        customer.setName(customerDTO.name());
        customer.setStreet(customerDTO.street());
        customer.setZipCode(customerDTO.zipCode());

        CustomerEntity savedCustomer = customerRepository.save(customer);
        return CustomerDTO.fromEntity(savedCustomer);
    }

    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        CustomerEntity existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Cliente não encontrado com o ID: " + id));

        customerDTO.validateForCreationOrUpdate();
        // Atualizar a entidade existente
        existingCustomer.setName(customerDTO.name());
        existingCustomer.setStreet(customerDTO.street());
        existingCustomer.setZipCode(customerDTO.zipCode());

        CustomerEntity updatedCustomer = customerRepository.save(existingCustomer);
        return CustomerDTO.fromEntity(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        CustomerEntity customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Cliente não encontrado com o ID: " + id));
        customerRepository.delete(customer);
        return ResponseEntity.ok().build();
    }
}
