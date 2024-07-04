package com.innovix.mapper;

import com.innovix.dto.PersonDTO;
import com.innovix.entity.Person;
import com.innovix.entity.PersonType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class PersonMapperTest {

    private final PersonMapper personMapper = PersonMapper.INSTANCE;
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    @Test
    void toDto() {
        // Arrange
        Person person = new Person();
        person.setId(1L);
        person.setEmail("test@example.com");
        person.setFullName("John Doe");
        person.setCpf("123.456.789-00");
        person.setPassword("password");
        person.setPhone("1234567890");
        person.setBirthday(LocalDate.of(1990, 1, 1));
        person.setType(PersonType.CUSTOMER);

        // Act
        PersonDTO personDTO = personMapper.toDto(person);

        // Assert
        assertNotNull(personDTO);
        assertEquals(1L, personDTO.getId());
        assertEquals("test@example.com", personDTO.getEmail());
        assertEquals("John Doe", personDTO.getFullName());
        assertEquals("123.456.789-00", personDTO.getCpf());
        assertEquals("password", personDTO.getPassword());
        assertEquals("1234567890", personDTO.getPhone());
        assertEquals("1990-01-01", personDTO.getBirthday());
        assertEquals("CUSTOMER", personDTO.getType());
    }

    @Test
    void toEntity() {
        // Arrange
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(1L);
        personDTO.setEmail("test@example.com");
        personDTO.setFullName("John Doe");
        personDTO.setCpf("123.456.789-00");
        personDTO.setPassword("password");
        personDTO.setPhone("1234567890");
        personDTO.setBirthday("1990-01-01");
        personDTO.setType("CUSTOMER");

        // Act
        Person person = personMapper.toEntity(personDTO);

        // Assert
        assertNotNull(person);
        assertEquals(1L, person.getId());
        assertEquals("test@example.com", person.getEmail());
        assertEquals("John Doe", person.getFullName());
        assertEquals("123.456.789-00", person.getCpf());
        assertEquals("password", person.getPassword());
        assertEquals("1234567890", person.getPhone());
        assertEquals(LocalDate.of(1990, 1, 1), person.getBirthday());
        assertEquals(PersonType.CUSTOMER, person.getType());
    }
}
