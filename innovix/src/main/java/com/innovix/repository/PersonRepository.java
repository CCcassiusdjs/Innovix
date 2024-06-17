package com.innovix.repository;

import com.innovix.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByEmail(String email);
    Person findByCpf(String cpf);
    List<Person> findByNameContaining(String name);
    List<Person> findByType(String type);
}
