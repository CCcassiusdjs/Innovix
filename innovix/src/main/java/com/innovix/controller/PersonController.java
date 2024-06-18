package com.innovix.controller;

import com.innovix.dto.PersonDTO;
import com.innovix.mapper.PersonMapper;
import com.innovix.usecase.PersonUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonUseCase personUseCase;

    @Autowired
    public PersonController(PersonUseCase personUseCase) {
        this.personUseCase = personUseCase;
    }

    @GetMapping
    public List<PersonDTO> listAll() {
        return personUseCase.listAllPersons().stream()
                .map(PersonMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public PersonDTO save(@RequestBody PersonDTO personDTO) {
        return PersonMapper.INSTANCE.toDto(
                personUseCase.createPerson(PersonMapper.INSTANCE.toEntity(personDTO))
        );
    }

    @GetMapping("/{id}")
    public PersonDTO getById(@PathVariable Long id) {
        return PersonMapper.INSTANCE.toDto(personUseCase.getPersonById(id));
    }

    @GetMapping("/email/{email}")
    public PersonDTO getByEmail(@PathVariable String email) {
        return PersonMapper.INSTANCE.toDto(personUseCase.getPersonByEmail(email));
    }

    @GetMapping("/cpf/{cpf}")
    public PersonDTO getByCpf(@PathVariable String cpf) {
        return PersonMapper.INSTANCE.toDto(personUseCase.getPersonByCpf(cpf));
    }

    @GetMapping("/name/{name}")
    public List<PersonDTO> listByNameContaining(@PathVariable String name) {
        return personUseCase.listPersonsByNameContaining(name).stream()
                .map(PersonMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/type/{type}")
    public List<PersonDTO> listByType(@PathVariable String type) {
        return personUseCase.listPersonsByType(type).stream()
                .map(PersonMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        personUseCase.deletePerson(id);
    }
}
