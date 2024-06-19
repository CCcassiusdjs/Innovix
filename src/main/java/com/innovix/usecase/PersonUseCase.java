package com.innovix.usecase;

import com.innovix.entity.Person;
import com.innovix.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Use case for managing operations related to {@link Person}.
 */
@Component
public class PersonUseCase {

    private final PersonService personService;

    /**
     * Constructor for dependency injection.
     *
     * @param personService the person service.
     */
    @Autowired
    public PersonUseCase(PersonService personService) {
        this.personService = personService;
    }

    /**
     * Lists all persons.
     *
     * @return a list of persons.
     */
    public List<Person> listAllPersons() {
        return personService.listAll();
    }

    /**
     * Creates a new person.
     *
     * @param person the person to create.
     * @return the created person.
     */
    public Person createPerson(Person person) {
        return personService.save(person);
    }

    /**
     * Gets a person by ID.
     *
     * @param id the ID of the person.
     * @return the found person, or {@code null} if not found.
     */
    public Person getPersonById(Long id) {
        return personService.findById(id);
    }

    /**
     * Gets a person by email.
     *
     * @param email the email of the person.
     * @return the found person.
     */
    public Person getPersonByEmail(String email) {
        return personService.findByEmail(email);
    }

    /**
     * Gets a person by CPF.
     *
     * @param cpf the CPF of the person.
     * @return the found person.
     */
    public Person getPersonByCpf(String cpf) {
        return personService.findByCpf(cpf);
    }

    /**
     * Lists persons by name containing.
     *
     * @param name the name to search.
     * @return a list of persons whose names contain the specified string.
     */
    public List<Person> listPersonsByNameContaining(String name) {
        return personService.findByNameContaining(name);
    }

    /**
     * Lists persons by type.
     *
     * @param type the type of the person.
     * @return a list of persons with the specified type.
     */
    public List<Person> listPersonsByType(String type) {
        return personService.findByType(type);
    }

    /**
     * Deletes a person by ID.
     *
     * @param id the ID of the person to delete.
     */
    public void deletePerson(Long id) {
        personService.delete(id);
    }
}
