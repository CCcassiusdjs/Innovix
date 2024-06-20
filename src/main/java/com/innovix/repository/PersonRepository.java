package com.innovix.repository;

import com.innovix.entity.Person;
import com.innovix.entity.PersonType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByEmail(String email);
    boolean existsByEmail(String email);
    List<Person> findByType(PersonType type);
}
