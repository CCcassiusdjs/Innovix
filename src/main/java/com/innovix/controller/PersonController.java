package com.innovix.controller;

import com.innovix.dto.AddressDTO;
import com.innovix.dto.OrderDTO;
import com.innovix.dto.PersonDTO;
import com.innovix.entity.Person;
import com.innovix.mapper.AddressMapper;
import com.innovix.mapper.OrderMapper;
import com.innovix.usecase.CustomerUseCase;
import com.innovix.usecase.EmployeeUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for managing persons.
 */
@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final CustomerUseCase customerUseCase;
    private final EmployeeUseCase employeeUseCase;

    @Autowired
    public PersonController(CustomerUseCase customerUseCase, EmployeeUseCase employeeUseCase) {
        this.customerUseCase = customerUseCase;
        this.employeeUseCase = employeeUseCase;
    }

    /**
     * Lists all employees.
     *
     * @return A list of all employees.
     */
    @GetMapping
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public List<PersonDTO> listAll() {
        return employeeUseCase.listAllEmployees();
    }

    /**
     * Lists all customers.
     *
     * @return A list of all customers.
     */
    @GetMapping("/customers")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public List<PersonDTO> listAllCustomers() {
        return customerUseCase.listAllCustomers();
    }

    /**
     * Creates a new address for a person.
     *
     * @param id          The ID of the person.
     * @param addressDTO  The address data transfer object.
     * @return The response entity containing the saved address data transfer object.
     */
    @PostMapping("/{id}/addresses")
    @PreAuthorize("permitAll()")
    public ResponseEntity<AddressDTO> createAddress(@PathVariable Long id, @RequestBody AddressDTO addressDTO) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loggedInUserEmail = userDetails.getUsername();
        PersonDTO loggedInUser = customerUseCase.getCustomerByEmail(loggedInUserEmail);

        // Debugging statements
        System.out.println("Logged-in user ID: " + loggedInUser.getId());
        System.out.println("Provided ID: " + id);
        System.out.println("Logged-in user type: " + loggedInUser.getType());

        if (!loggedInUser.getId().equals(id) && !loggedInUser.getType().equals("EMPLOYEE")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        addressDTO.setPersonId(id);
        AddressDTO savedAddress = AddressMapper.INSTANCE.toDto(employeeUseCase.createAddress(AddressMapper.INSTANCE.toEntity(addressDTO)));
        return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
    }


    /**
     * Lists orders by customer ID.
     *
     * @param customerId  The ID of the customer.
     * @return The response entity containing the list of orders associated with the customer.
     */
    @GetMapping("/customer-orders/{customerId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<OrderDTO>> listOrdersByCustomer(@PathVariable Long customerId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loggedInUserEmail = userDetails.getUsername();
        PersonDTO loggedInUser = customerUseCase.getCustomerByEmail(loggedInUserEmail);

        if (!loggedInUser.getId().equals(customerId)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        List<OrderDTO> orders = customerUseCase.listOrdersByCustomer(new Person(customerId)).stream()
                .map(OrderMapper.INSTANCE::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    /**
     * Gets a person by ID.
     *
     * @param id  The ID of the person.
     * @return The person data transfer object.
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public PersonDTO getById(@PathVariable Long id) {
        return employeeUseCase.getEmployeeById(id);
    }

    /**
     * Deletes an employee by ID.
     *
     * @param id  The ID of the employee to delete.
     * @return The response entity with no content.
     */
    @DeleteMapping("/employee/{id}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loggedInUserEmail = userDetails.getUsername();
        PersonDTO loggedInUser = employeeUseCase.getEmployeeByEmail(loggedInUserEmail);

        if (loggedInUser.getId().equals(id)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        employeeUseCase.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Deletes a customer by ID.
     *
     * @param id  The ID of the customer to delete.
     * @return The response entity with no content.
     */
    @DeleteMapping("/customer/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loggedInUserEmail = userDetails.getUsername();
        PersonDTO loggedInUser = customerUseCase.getCustomerByEmail(loggedInUserEmail);

        if (!loggedInUser.getId().equals(id)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        customerUseCase.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Registers a new customer.
     *
     * @param personDTO  The person data transfer object.
     * @return The response entity indicating the result of the registration.
     */
    @PostMapping("/registerCustomer")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> registerCustomer(@RequestBody PersonDTO personDTO) {
        if (customerUseCase.userExists(personDTO.getEmail())) {
            return new ResponseEntity<>("User with this email already exists", HttpStatus.CONFLICT);
        }
        customerUseCase.registerCustomer(personDTO);
        return new ResponseEntity<>("Customer registered successfully", HttpStatus.CREATED);
    }

    /**
     * Registers a new employee.
     *
     * @param personDTO  The person data transfer object.
     * @return The response entity indicating the result of the registration.
     */
    @PostMapping("/registerEmployee")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public ResponseEntity<?> registerEmployee(@RequestBody PersonDTO personDTO) {
        if (employeeUseCase.userExists(personDTO.getEmail())) {
            return new ResponseEntity<>("User with this email already exists", HttpStatus.CONFLICT);
        }
        employeeUseCase.registerEmployee(personDTO);
        return new ResponseEntity<>("Employee registered successfully", HttpStatus.CREATED);
    }
}
