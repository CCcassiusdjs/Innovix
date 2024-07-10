package com.innovix.service;

import com.innovix.dto.PersonDTO;
import com.innovix.entity.Person;
import com.innovix.entity.PersonType;
import com.innovix.exception.UserExistentException;
import com.innovix.mapper.PersonMapper;
import com.innovix.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing persons.
 * <p>
 * This class provides methods for CRUD operations on Person entities and user authentication.
 * </p>
 */
@Service
public class PersonService implements UserDetailsService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructs a new {@code PersonService} with the specified {@link PersonRepository} and {@link PasswordEncoder}.
     *
     * @param personRepository the repository for accessing person data
     * @param passwordEncoder  the encoder for encoding passwords
     */
    @Autowired
    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Loads the user by their username (email).
     * <p>
     * This method is used to retrieve the {@link Person} entity based on their email.
     * </p>
     *
     * @param username the email of the person
     * @return the {@link UserDetails} of the person
     * @throws UsernameNotFoundException if no person is found with the specified email
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByEmail(username);
        if (person == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        return person;
    }

    /**
     * Checks if a user exists by email.
     *
     * @param email The email to check.
     * @return True if a user with the specified email exists, false otherwise.
     */
    public boolean userExists(String email) {
        return personRepository.existsByEmail(email);
    }

    /**
     * Lists all persons.
     *
     * @return A list of all persons.
     */
    public List<PersonDTO> listAll() {
        return personRepository.findAll().stream()
                .map(PersonMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Finds a person by email.
     *
     * @param email The email of the person.
     * @return The person with the specified email.
     * @throws UsernameNotFoundException If no person is found with the specified email.
     */
    public PersonDTO findByEmail(String email) {
        Person person = personRepository.findByEmail(email);
        if (person == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return PersonMapper.INSTANCE.toDto(person);
    }

    /**
     * Finds a person by ID.
     *
     * @param id The ID of the person.
     * @return The person with the specified ID, or null if not found.
     * @throws UsernameNotFoundException If no person is found with the specified ID.
     */
    public PersonDTO findById(Long id) {
        Person person = personRepository.findById(id).orElse(null);
        if (person == null) {
            throw new UsernameNotFoundException("User not found with id: " + id);
        }
        return PersonMapper.INSTANCE.toDto(person);
    }

    /**
     * Saves a new person or updates an existing one.
     *
     * @param personDTO The person data transfer object containing the person's details.
     * @return The saved person.
     */
    public PersonDTO save(PersonDTO personDTO) {
        Person person = PersonMapper.INSTANCE.toEntity(personDTO);
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person = personRepository.save(person);
        return PersonMapper.INSTANCE.toDto(person);
    }

    /**
     * Deletes a person by ID.
     *
     * @param id The ID of the person to delete.
     * @throws UsernameNotFoundException If no person is found with the specified ID.
     */
    public void delete(Long id) {
        if (!personRepository.existsById(id)) {
            throw new UsernameNotFoundException("User not found with id: " + id);
        }
        personRepository.deleteById(id);
    }

    /**
     * Registers a new customer.
     *
     * @param personDTO The person data transfer object containing the customer's details.
     * @throws UserExistentException If a user with the specified email already exists.
     */
    public void registerCustomer(PersonDTO personDTO) {
        if (userExists(personDTO.getEmail())) {
            throw new UserExistentException("User already exists with email: " + personDTO.getEmail());
        }
        Person person = PersonMapper.INSTANCE.toEntity(personDTO);
        person.setType(PersonType.CUSTOMER);
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personRepository.save(person);
    }

    /**
     * Registers a new employee.
     *
     * @param personDTO The person data transfer object containing the employee's details.
     * @throws UserExistentException If a user with the specified email already exists.
     */
    public void registerEmployee(PersonDTO personDTO) {
        if (userExists(personDTO.getEmail())) {
            throw new UserExistentException("User already exists with email: " + personDTO.getEmail());
        }
        Person person = PersonMapper.INSTANCE.toEntity(personDTO);
        person.setType(PersonType.EMPLOYEE);
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personRepository.save(person);
    }

    /**
     * Lists all persons by type.
     *
     * @param type The type of the persons to list.
     * @return A list of persons with the specified type.
     */
    public List<PersonDTO> listAllByType(PersonType type) {
        return personRepository.findByType(type).stream()
                .map(PersonMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }
}
