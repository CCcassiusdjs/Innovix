package br.com.innovix.controller;

import br.com.innovix.dto.PersonDTOs;
import br.com.innovix.service.PeopleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
public class PeopleControllers {

    @RestController
    @RequestMapping("/customers")
    public static class CustomerController {

        private final PeopleServices.CustomerService customerService;

        @Autowired
        public CustomerController(PeopleServices.CustomerService customerService) {
            this.customerService = customerService;
        }

        @GetMapping
        public List<PersonDTOs.CustomerDTO> getAllCustomers() {
            return customerService.findAllCustomers();
        }

        
        @GetMapping("/{id}")
        public PersonDTOs.CustomerDTO getCustomerById(@PathVariable Long id) {
            return customerService.findCustomerById(id);
        }

        
        @PostMapping
        public PersonDTOs.CustomerDTO addCustomer(@RequestBody PersonDTOs.CustomerDTO customerDTO) {
            return customerService.addCustomer(customerDTO);
        }

        
        @PutMapping("/{id}")
        public PersonDTOs.CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody PersonDTOs.CustomerDTO customerDTO) {
            return customerService.updateCustomer(id, customerDTO);
        }

        
        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
            customerService.deleteCustomer(id);
            return ResponseEntity.ok().build();
        }
    }

    @RestController
    @RequestMapping("/employees")
    public static class EmployeeController {

        private final PeopleServices.EmployeeService employeeService;

        @Autowired
        public EmployeeController(PeopleServices.EmployeeService employeeService) {
            this.employeeService = employeeService;
        }

        @GetMapping
        public List<PersonDTOs.EmployeeDTO> getAllEmployees() {
            return employeeService.findAllEmployees();
        }

        
        @GetMapping("/{id}")
        public PersonDTOs.EmployeeDTO getEmployeeById(@PathVariable Long id) {
            return employeeService.findEmployeeById(id);
        }

        
        @PostMapping
        public PersonDTOs.EmployeeDTO addEmployee(@RequestBody PersonDTOs.EmployeeDTO employeeDTO) {
            return employeeService.addEmployee(employeeDTO);
        }

        
        @PutMapping("/{id}")
        public PersonDTOs.EmployeeDTO updateEmployee(@PathVariable Long id, @RequestBody PersonDTOs.EmployeeDTO employeeDTO) {
            return employeeService.updateEmployee(id, employeeDTO);
        }

        
        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok().build();
        }
    }
}
