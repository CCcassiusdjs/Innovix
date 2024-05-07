package br.com.innovix.controller;

import br.com.innovix.domain.person.PersonDTO;
import br.com.innovix.domain.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Service
@Validated
@RestController
public class PersonController {

    private final PersonService peopleService;

    @Autowired
    public PersonController(PersonService peopleService) {
        this.peopleService = peopleService;
    }

    // Generic methods to handle both customers and employees
    @GetMapping("/{type}")
    public ResponseEntity<List<?>> getAllPeople(@PathVariable String type) {
            return ResponseEntity.ok(peopleService.findAll(type));
    }

    @GetMapping("/{type}/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable String type, @PathVariable Long id) {
            return ResponseEntity.ok(peopleService.findById(id,type));
    }

    @PostMapping("/{type}")
    public ResponseEntity<?> addPerson(@PathVariable String type, @RequestBody PersonDTO personDTO) {
            return ResponseEntity.ok(peopleService.addPerson(personDTO));
    }

    @PutMapping("/{type}/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable String type, @PathVariable Long id, @RequestBody PersonDTO personDTO) {
            return ResponseEntity.ok(peopleService.updatePerson(id, personDTO));
    }

    @DeleteMapping("/{type}/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable String type, @PathVariable Long id) {
            peopleService.deletePerson(id, type);
            return ResponseEntity.ok().build();
    }
}
