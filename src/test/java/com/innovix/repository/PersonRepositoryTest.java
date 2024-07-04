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
        Person person1 = new Person(null, "john.doe@example.com", "John Doe", "123.456.789-00", "password", "123456789", LocalDate.of(1990, 1, 1), PersonType.USER);
        Person person2 = new Person(null, "jane.smith@example.com", "Jane Smith", "987.654.321-00", "password", "987654321", LocalDate.of(1985, 5, 5), PersonType.ADMIN);
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
        List<Person> users = personRepository.findByType(PersonType.USER);
        List<Person> admins = personRepository.findByType(PersonType.ADMIN);

        assertEquals(1, users.size());
        assertEquals(1, admins.size());

        assertEquals("John Doe", users.get(0).getFullName());
        assertEquals("Jane Smith", admins.get(0).getFullName());
    }
}
