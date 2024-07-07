package com.innovix.service;

import com.innovix.dto.PersonDTO;
import com.innovix.entity.Person;
import com.innovix.entity.PersonType;
import com.innovix.exception.UserExistentException;
import com.innovix.mapper.PersonMapper;
import com.innovix.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private PersonService personService;

    private Person person1;
    private Person person2;

    @BeforeEach
    void setUp() {
        person1 = new Person();
        person1.setId(1L);
        person1.setEmail("john.doe@example.com");
        person1.setPassword("password");
        person1.setType(PersonType.CUSTOMER);

        person2 = new Person();
        person2.setId(2L);
        person2.setEmail("jane.smith@example.com");
        person2.setPassword("password");
        person2.setType(PersonType.EMPLOYEE);
    }

    @Test
    void testLoadUserByUsername() {
        when(personRepository.findByEmail("john.doe@example.com")).thenReturn(person1);

        assertEquals(person1, personService.loadUserByUsername("john.doe@example.com"));
    }

    @Test
    void testUserExists() {
        when(personRepository.existsByEmail("john.doe@example.com")).thenReturn(true);

        assertTrue(personService.userExists("john.doe@example.com"));
    }

    @Test
    void testListAll() {
        when(personRepository.findAll()).thenReturn(Arrays.asList(person1, person2));

        List<PersonDTO> persons = personService.listAll();

        assertNotNull(persons);
        assertEquals(2, persons.size());
        assertEquals(person1.getEmail(), persons.get(0).getEmail());
        assertEquals(person2.getEmail(), persons.get(1).getEmail());
        assertEquals(person1.getType(), persons.get(0).getType());
        assertEquals(person2.getType(), persons.get(1).getType());

        verify(personRepository, times(1)).findAll();
    }

    @Test
    void testFindByEmail() {
        when(personRepository.findByEmail("john.doe@example.com")).thenReturn(person1);

        PersonDTO foundPerson = personService.findByEmail("john.doe@example.com");

        assertNotNull(foundPerson);
        assertEquals("john.doe@example.com", foundPerson.getEmail());
        assertEquals(PersonType.CUSTOMER, foundPerson.getType());
    }

    @Test
    void testFindById() {
        when(personRepository.findById(1L)).thenReturn(Optional.of(person1));

        PersonDTO foundPerson = personService.findById(1L);

        assertNotNull(foundPerson);
        assertEquals(1L, foundPerson.getId());
        assertEquals("john.doe@example.com", foundPerson.getEmail());
        assertEquals(PersonType.CUSTOMER, foundPerson.getType());
    }

    @Test
    void testSave() {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setEmail("john.doe@example.com");
        personDTO.setPassword("password");

        when(personRepository.save(any(Person.class))).thenAnswer(invocation -> {
            Person person = invocation.getArgument(0);
            person.setId(1L); // Mocking the saved person's ID
            return person;
        });

        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        PersonDTO savedPerson = personService.save(personDTO);

        assertNotNull(savedPerson);
        assertEquals(1L, savedPerson.getId());
        assertEquals("john.doe@example.com", savedPerson.getEmail());
        assertEquals("encodedPassword", savedPerson.getPassword());
    }

    @Test
    void testDelete() {
        doNothing().when(personRepository).deleteById(1L);

        personService.delete(1L);

        verify(personRepository, times(1)).deleteById(1L);
    }

    @Test
    void testRegisterCustomer() {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setEmail("john.doe@example.com");
        personDTO.setPassword("password");

        when(personRepository.existsByEmail("john.doe@example.com")).thenReturn(false);

        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        personService.registerCustomer(personDTO);

        verify(personRepository, times(1)).save(any(Person.class));
    }

    @Test
    void testRegisterCustomerUserExistsException() {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setEmail("john.doe@example.com");
        personDTO.setPassword("password");

        when(personRepository.existsByEmail("john.doe@example.com")).thenReturn(true);

        assertThrows(UserExistentException.class, () -> personService.registerCustomer(personDTO));

        verify(personRepository, never()).save(any());
    }

    @Test
    void testRegisterEmployee() {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setEmail("jane.smith@example.com");
        personDTO.setPassword("password");

        when(personRepository.existsByEmail("jane.smith@example.com")).thenReturn(false);

        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        personService.registerEmployee(personDTO);

        verify(personRepository, times(1)).save(any(Person.class));
    }

    @Test
    void testRegisterEmployeeUserExistsException() {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setEmail("jane.smith@example.com");
        personDTO.setPassword("password");

        when(personRepository.existsByEmail("jane.smith@example.com")).thenReturn(true);

        assertThrows(UserExistentException.class, () -> personService.registerEmployee(personDTO));

        verify(personRepository, never()).save(any());
    }

    @Test
    void testListAllByType() {
        when(personRepository.findByType(PersonType.CUSTOMER)).thenReturn(Arrays.asList(person1));

        List<PersonDTO> customers = personService.listAllByType(PersonType.CUSTOMER);

        assertNotNull(customers);
        assertEquals(1, customers.size());
        assertEquals("john.doe@example.com", customers.get(0).getEmail());
        assertEquals(PersonType.CUSTOMER, customers.get(0).getType());

        verify(personRepository, times(1)).findByType(PersonType.CUSTOMER);
    }
}
