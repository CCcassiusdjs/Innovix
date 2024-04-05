package br.com.innovix.controller;

import br.com.innovix.dto.EmployeeDTO;
import br.com.innovix.entity.EmployeeEntity;
import br.com.innovix.exception.EmployeeNotFoundException;
import br.com.innovix.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable Long id) {
        EmployeeEntity employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Funcionário não encontrado com o ID: " + id));
        return convertToDto(employee);
    }

    @PostMapping
    public EmployeeDTO addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeEntity employee = convertToEntity(employeeDTO);
        EmployeeEntity savedEmployee = employeeRepository.save(employee);
        return convertToDto(savedEmployee);
    }

    // Métodos PUT e DELETE conforme necessário

    private EmployeeDTO convertToDto(EmployeeEntity employeeEntity) {
        EmployeeDTO employeeDTO = EmployeeDTO.createEmployeeDTO();
        employeeDTO.setCodEmployee((long) employeeEntity.getCodEmployee());
        employeeDTO.setName(employeeEntity.getName());
        employeeDTO.setStreet(employeeEntity.getStreet());
        employeeDTO.setZipCode(employeeEntity.getZipCode());
        return employeeDTO;
    }

    private EmployeeEntity convertToEntity(EmployeeDTO employeeDTO) {
        // Set fields from DTO to Entity
        return new EmployeeEntity();
    }
}
