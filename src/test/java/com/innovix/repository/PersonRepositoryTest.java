package com.innovix.repository;

import com.innovix.entity.Person;
import com.innovix.entity.PersonType;
import com.innovix.repository.PersonRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        Person person1 = new Person(1L);
        person1.setType(PersonType.EMPLOYEE);
        person1.setFullName("John Doe");
        person1.setEmail("john.doe@example.com");

        Person person2 = new Person(2L);
        person2.setType(PersonType.CUSTOMER);
        person2.setFullName("Jane Smith");
        person2.setEmail("jane.smith@example.com");

        personRepository.save(person1);
        personRepository.save(person2);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        personRepository.deleteAll();
    }

    @org.junit.jupiter.api.Test
    void findByEmail() {
        Person foundPerson = personRepository.findByEmail("john.doe@example.com");
        assertNotNull(foundPerson);
        assertEquals("John Doe", foundPerson.getFullName());
    }

    @org.junit.jupiter.api.Test
    void existsByEmail() {
        assertTrue(personRepository.existsByEmail("john.doe@example.com"));
        assertFalse(personRepository.existsByEmail("nonexistent@example.com"));
    }

    @org.junit.jupiter.api.Test
    void findByType() {
        List<Person> customers = personRepository.findByType(PersonType.CUSTOMER);
        List<Person> employees = personRepository.findByType(PersonType.EMPLOYEE);

        assertEquals(1, customers.size());
        assertEquals(1, employees.size());
    }
}
