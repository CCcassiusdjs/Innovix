package br.com.innovix.service;

import br.com.innovix.dto.PersonDTO;
import br.com.innovix.entity.person.PersonEntity;
import br.com.innovix.exception.Exceptions;
import br.com.innovix.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<PersonDTO> findAll(String type) {
        if (type.equalsIgnoreCase("customer")) {
            return personRepository.findAll().stream()
                    .map(PersonDTO::fromEntity)
                    .collect(Collectors.toList());
        }
        throw new IllegalArgumentException("Invalid Type: " + type);
    }

    public PersonDTO findById(Long id, String type) {
        if (type.equalsIgnoreCase("customer")) {
            PersonEntity customer = personRepository.findById(id)
                    .orElseThrow(() -> new Exceptions.CustomerNotFoundException("Cliente não encontrado com o ID: " + id));
            return PersonDTO.fromEntity(customer);
        } else if (type.equalsIgnoreCase("employee")) {
            PersonEntity employee = personRepository.findById(id)
                    .orElseThrow(() -> new Exceptions.EmployeeNotFoundException("Funcionário não encontrado com o ID: " + id));
            return PersonDTO.fromEntity(employee);
        }
        throw new IllegalArgumentException("Tipo inválido: " + type);
    }

    public PersonDTO addPerson(PersonDTO personDTO) {
        personDTO.validateForCreationOrUpdate();
        if (personDTO.type().equalsIgnoreCase("customer")) {
            PersonEntity customer = (PersonEntity) personDTO.toEntity();
            return PersonDTO.fromEntity(personRepository.save(customer));
        } else if (personDTO.type().equalsIgnoreCase("employee")) {
            PersonEntity employee = (PersonEntity) personDTO.toEntity();
            return PersonDTO.fromEntity(personRepository.save(employee));
        }
        throw new IllegalArgumentException("Tipo inválido: " + personDTO.type());
    }

    public PersonDTO updatePerson(Long id, PersonDTO personDTO) {
        personDTO.validateForCreationOrUpdate();
        if (personDTO.type().equalsIgnoreCase("customer")) {
            PersonEntity customer = personRepository.findById(id)
                    .orElseThrow(() -> new Exceptions.CustomerNotFoundException("Cliente não encontrado com o ID: " + id));
            copyDtoToEntity(personDTO, customer);
            return PersonDTO.fromEntity(personRepository.save(customer));
        } else if (personDTO.type().equalsIgnoreCase("employee")) {
            PersonEntity employee = personRepository.findById(id)
                    .orElseThrow(() -> new Exceptions.EmployeeNotFoundException("Funcionário não encontrado com o ID: " + id));
            copyDtoToEntity(personDTO, employee);
            return PersonDTO.fromEntity(personRepository.save(employee));
        }
        throw new IllegalArgumentException("Tipo inválido: " + personDTO.type());
    }

    public void deletePerson(Long id, String type) {
        if (type.equalsIgnoreCase("customer")) {
            PersonEntity customer = personRepository.findById(id)
                    .orElseThrow(() -> new Exceptions.CustomerNotFoundException("Cliente não encontrado com o ID: " + id));
            personRepository.delete(customer);
        } else if (type.equalsIgnoreCase("employee")) {
            PersonEntity employee = personRepository.findById(id)
                    .orElseThrow(() -> new Exceptions.EmployeeNotFoundException("Funcionário não encontrado com o ID: " + id));
            personRepository.delete(employee);
        } else {
            throw new IllegalArgumentException("Tipo inválido: " + type);
        }
    }

    private void copyDtoToEntity(PersonDTO dto, Object entity) {
        if (entity instanceof PersonEntity person) {  // Corrected: Single check, handling any PersonEntity
            person.setName(dto.name());
            person.setStreet(dto.street());
            person.setZipCode(dto.zipCode());
        } else {
            throw new IllegalArgumentException("Invalid entity type; must be PersonEntity");
        }
    }

}
