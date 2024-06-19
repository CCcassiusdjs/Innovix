package com.innovix.repository;

import com.innovix.entity.Person;
import com.innovix.entity.PersonType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByEmail(String email);
    boolean existsByEmail(String email);

    // Additional methods to filter by type
    List<Person> findByType(PersonType type);
    Person findByEmailAndType(String email, PersonType type);
}
