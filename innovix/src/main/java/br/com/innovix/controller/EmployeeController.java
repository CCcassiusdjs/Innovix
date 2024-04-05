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
                .map(EmployeeDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable Long id) {
        EmployeeEntity employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Funcionário não encontrado com o ID: " + id));
        return EmployeeDTO.fromEntity(employee);
    }

    @PostMapping
    public EmployeeDTO addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        // Validação pode ser adicionada aqui, se necessário
        EmployeeEntity employee = new EmployeeEntity();
        // Preencher a entidade com base nos dados do DTO
        employee.setName(employeeDTO.name());
        employee.setStreet(employeeDTO.street());
        employee.setZipCode(employeeDTO.zipCode());

        EmployeeEntity savedEmployee = employeeRepository.save(employee);
        return EmployeeDTO.fromEntity(savedEmployee);
    }

    @PutMapping("/{id}")
    public EmployeeDTO updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        EmployeeEntity existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Funcionário não encontrado com o ID: " + id));

        // Atualizar a entidade existente
        existingEmployee.setName(employeeDTO.name());
        existingEmployee.setStreet(employeeDTO.street());
        existingEmployee.setZipCode(employeeDTO.zipCode());

        EmployeeEntity updatedEmployee = employeeRepository.save(existingEmployee);
        return EmployeeDTO.fromEntity(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        EmployeeEntity employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Funcionário não encontrado com o ID: " + id));
        employeeRepository.delete(employee);
        return ResponseEntity.ok().build();
    }
}
