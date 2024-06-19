package com.innovix.controller;

import com.innovix.dto.PersonDTO;
import com.innovix.usecase.PersonUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonUseCase personUseCase;

    @Autowired
    public PersonController(PersonUseCase personUseCase) {
        this.personUseCase = personUseCase;
    }

    @GetMapping
    public List<PersonDTO> listAll() {
        return personUseCase.listAll();
    }

    @GetMapping("/{id}")
    public PersonDTO getById(@PathVariable Long id) {
        return personUseCase.getById(id);
    }

    @PostMapping
    public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO personDTO) {
        PersonDTO createdPerson = personUseCase.create(personDTO);
        return new ResponseEntity<>(createdPerson, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/registerCustomer")
    public ResponseEntity<String> registerCustomer(@RequestBody PersonDTO personDTO) {
        personUseCase.registerCustomer(personDTO);
        return new ResponseEntity<>("Customer registered successfully.", HttpStatus.CREATED);
    }

    @PostMapping("/registerEmployee")
    public ResponseEntity<String> registerEmployee(@RequestBody PersonDTO personDTO) {
        personUseCase.registerEmployee(personDTO);
        return new ResponseEntity<>("Employee registered successfully.", HttpStatus.CREATED);
    }
}
