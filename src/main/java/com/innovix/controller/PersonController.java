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

    @GetMapping
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public List<PersonDTO> listAll() {
        return employeeUseCase.listAllEmployees();
    }

    @GetMapping("/customers")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public List<PersonDTO> listAllCustomers() {
        return customerUseCase.listAllCustomers();
    }

    @PostMapping("/{id}/addresses")
    @PreAuthorize("permitAll()")
    public ResponseEntity<AddressDTO> createAddress(@PathVariable Long id, @RequestBody AddressDTO addressDTO) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loggedInUserEmail = userDetails.getUsername();
        PersonDTO loggedInUser = customerUseCase.getCustomerByEmail(loggedInUserEmail);

        if (!loggedInUser.getId().equals(id) && !loggedInUser.getType().equals("EMPLOYEE")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        addressDTO.setPersonId(id);
        AddressDTO savedAddress = AddressMapper.INSTANCE.toDto(employeeUseCase.createAddress(AddressMapper.INSTANCE.toEntity(addressDTO)));
        return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
    }



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

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public PersonDTO getById(@PathVariable Long id) {
        return employeeUseCase.getEmployeeById(id);
    }

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

    @PostMapping("/registerCustomer")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> registerCustomer(@RequestBody PersonDTO personDTO) {
        if (customerUseCase.userExists(personDTO.getEmail())) {
            return new ResponseEntity<>("User with this email already exists", HttpStatus.CONFLICT);
        }
        customerUseCase.registerCustomer(personDTO);
        return new ResponseEntity<>("Customer registered successfully", HttpStatus.CREATED);
    }

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
