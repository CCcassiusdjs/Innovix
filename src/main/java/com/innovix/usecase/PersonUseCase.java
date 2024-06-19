package com.innovix.usecase;

import com.innovix.dto.PersonDTO;
import com.innovix.entity.Person;
import com.innovix.mapper.PersonMapper;
import com.innovix.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonUseCase {

    private final PersonService personService;

    @Autowired
    public PersonUseCase(PersonService personService) {
        this.personService = personService;
    }

    public List<PersonDTO> listAll() {
        return personService.listAll();
    }

    public PersonDTO getById(Long id) {
        return personService.findById(id);
    }

    public PersonDTO create(PersonDTO personDTO) {
        return personService.save(personDTO);
    }

    public void delete(Long id) {
        personService.delete(id);
    }

    public void registerCustomer(PersonDTO personDTO) {
        personService.registerCustomer(personDTO);
    }

    public void registerEmployee(PersonDTO personDTO) {
        personService.registerEmployee(personDTO);
    }
}
