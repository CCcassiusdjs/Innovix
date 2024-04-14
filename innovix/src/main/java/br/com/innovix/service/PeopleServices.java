package br.com.innovix.service;

import br.com.innovix.dto.PersonDTOs;
import br.com.innovix.entity.PersonEntities;
import br.com.innovix.exception.Exceptions;
import br.com.innovix.repository.PersonRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public class PeopleServices {

    @Service
    public static class CustomerService {

        @Autowired
        private PersonRepositories.CustomerRepository customerRepository;

        public List<PersonDTOs.CustomerDTO> findAllCustomers() {
            return customerRepository.findAll().stream()
                    .map(PersonDTOs.CustomerDTO::fromEntity)
                    .collect(Collectors.toList());
        }

        public PersonDTOs.CustomerDTO findCustomerById(Long id) {
            return customerRepository.findById(id)
                    .map(PersonDTOs.CustomerDTO::fromEntity)
                    .orElseThrow(() -> new Exceptions.CustomerNotFoundException("Cliente não encontrado com o ID: " + id));
        }

        public PersonDTOs.CustomerDTO addCustomer(PersonDTOs.CustomerDTO customerDTO) {
            customerDTO.validateForCreationOrUpdate();
            PersonEntities.CustomerEntity customer = new PersonEntities.CustomerEntity();
            copyDtoToEntity(customerDTO, customer);
            return PersonDTOs.CustomerDTO.fromEntity(customerRepository.save(customer));
        }

        public PersonDTOs.CustomerDTO updateCustomer(Long id, PersonDTOs.CustomerDTO customerDTO) {
            PersonEntities.CustomerEntity customer = customerRepository.findById(id)
                    .orElseThrow(() -> new Exceptions.CustomerNotFoundException("Cliente não encontrado com o ID: " + id));
            copyDtoToEntity(customerDTO, customer);
            return PersonDTOs.CustomerDTO.fromEntity(customerRepository.save(customer));
        }

        public void deleteCustomer(Long id) {
            PersonEntities.CustomerEntity customer = customerRepository.findById(id)
                    .orElseThrow(() -> new Exceptions.CustomerNotFoundException("Cliente não encontrado com o ID: " + id));
            customerRepository.delete(customer);
        }

        private void copyDtoToEntity(PersonDTOs.CustomerDTO customerDTO, PersonEntities.CustomerEntity customer) {
            customer.setName(customerDTO.name());
            customer.setStreet(customerDTO.street());
            customer.setZipCode(customerDTO.zipCode());
        }
    }

    @Service
    public static class EmployeeService {

        @Autowired
        private PersonRepositories.EmployeeRepository employeeRepository;

        public List<PersonDTOs.EmployeeDTO> findAllEmployees() {
            return employeeRepository.findAll().stream()
                    .map(PersonDTOs.EmployeeDTO::fromEntity)
                    .collect(Collectors.toList());
        }

        public PersonDTOs.EmployeeDTO findEmployeeById(Long id) {
            PersonEntities.EmployeeEntity employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new Exceptions.EmployeeNotFoundException("Funcionário não encontrado com o ID: " + id));
            return PersonDTOs.EmployeeDTO.fromEntity(employee);
        }

        public PersonDTOs.EmployeeDTO addEmployee(PersonDTOs.EmployeeDTO employeeDTO) {
            PersonEntities.EmployeeEntity employee = employeeDTO.toEntity();
            return PersonDTOs.EmployeeDTO.fromEntity(employeeRepository.save(employee));
        }

        public PersonDTOs.EmployeeDTO updateEmployee(Long id, PersonDTOs.EmployeeDTO employeeDTO) {
            PersonEntities.EmployeeEntity existingEmployee = employeeRepository.findById(id)
                    .orElseThrow(() -> new Exceptions.EmployeeNotFoundException("Funcionário não encontrado com o ID: " + id));
            existingEmployee.setName(employeeDTO.name());
            existingEmployee.setStreet(employeeDTO.street());
            existingEmployee.setZipCode(employeeDTO.zipCode());
            return PersonDTOs.EmployeeDTO.fromEntity(employeeRepository.save(existingEmployee));
        }

        public void deleteEmployee(Long id) {
            PersonEntities.EmployeeEntity employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new Exceptions.EmployeeNotFoundException("Funcionário não encontrado com o ID: " + id));
            employeeRepository.delete(employee);
        }
    }
}
