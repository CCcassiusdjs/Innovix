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
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable Long id) {
        CustomerEntity customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Cliente não encontrado com o ID: " + id));
        return convertToDto(customer);
    }

    @PostMapping
    public CustomerDTO addCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerEntity customer = convertToEntity(customerDTO);
        CustomerEntity savedCustomer = customerRepository.save(customer);
        return convertToDto(savedCustomer);
    }

    // Métodos PUT e DELETE, conforme necessário

    private CustomerDTO convertToDto(CustomerEntity customerEntity) {
        CustomerDTO customerDTO = CustomerDTO.createCustomerDTO();
        customerDTO.setCodCustomer((long) customerEntity.getCodCustomer());
        customerDTO.setName(customerEntity.getName());
        customerDTO.setStreet(customerEntity.getStreet());
        customerDTO.setZipCode(customerEntity.getZipCode());
        return customerDTO;
    }

    private CustomerEntity convertToEntity(CustomerDTO customerDTO) {
        // Set fields from DTO to Entity
        return new CustomerEntity();
    }
}
