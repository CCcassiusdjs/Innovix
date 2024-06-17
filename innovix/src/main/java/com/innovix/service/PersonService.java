package com.innovix.service;

import com.innovix.entity.Person;
import com.innovix.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to manage operations related to {@link Person}.
 */
@Service
public class PersonService {

    private final PersonRepository personRepository;

    /**
     * Constructor for dependency injection.
     *
     * @param personRepository the person repository.
     */
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Lists all persons.
     *
     * @return a list of persons.
     */
    public List<Person> listAll() {
        return personRepository.findAll();
    }

    /**
     * Saves a new person.
     *
     * @param person the person to save.
     * @return the saved person.
     */
    public Person save(Person person) {
        return personRepository.save(person);
    }

    /**
     * Finds a person by ID.
     *
     * @param id the ID of the person.
     * @return the found person, or {@code null} if not found.
     */
    public Person findById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    /**
     * Finds a person by email.
     *
     * @param email the email of the person.
     * @return the found person.
     */
    public Person findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    /**
     * Finds a person by CPF.
     *
     * @param cpf the CPF of the person.
     * @return the found person.
     */
    public Person findByCpf(String cpf) {
        return personRepository.findByCpf(cpf);
    }

    /**
     * Finds persons by name containing.
     *
     * @param name the name to search.
     * @return a list of persons whose names contain the specified string.
     */
    public List<Person> findByNameContaining(String name) {
        return personRepository.findByNameContaining(name);
    }

    /**
     * Finds persons by type.
     *
     * @param type the type of the person.
     * @return a list of persons with the specified type.
     */
    public List<Person> findByType(String type) {
        return personRepository.findByType(type);
    }

    /**
     * Deletes a person by ID.
     *
     * @param id the ID of the person to delete.
     */
    public void delete(Long id) {
        personRepository.deleteById(id);
    }
}
