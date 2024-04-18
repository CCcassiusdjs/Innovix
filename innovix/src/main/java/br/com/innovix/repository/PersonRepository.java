package br.com.innovix.repository;

import br.com.innovix.entity.person.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    // Additional query methods can be defined here
}