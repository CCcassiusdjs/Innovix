package com.innovix.repository;

import com.innovix.entity.Person;
import com.innovix.entity.PersonType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for Person entity.
 * <p>
 * This interface extends JpaRepository to provide CRUD operations for Person entities.
 * </p>
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

    /**
     * Finds a person by email.
     *
     * @param email The email of the person.
     * @return The person with the specified email.
     */
    Person findByEmail(String email);

    /**
     * Checks if a person exists by email.
     *
     * @param email The email to check.
     * @return True if a person with the specified email exists, false otherwise.
     */
    boolean existsByEmail(String email);

    /**
     * Finds persons by type.
     *
     * @param type The type of the person.
     * @return A list of persons with the specified type.
     */
    List<Person> findByType(PersonType type);
}
