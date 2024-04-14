package br.com.innovix.repository;

import br.com.innovix.entity.PersonEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public class PersonRepositories {

    @Repository
    public interface CustomerRepository extends JpaRepository<PersonEntities.CustomerEntity, Long> {
        // Additional query methods can be defined here
    }

    @Repository
    public interface EmployeeRepository extends JpaRepository<PersonEntities.EmployeeEntity, Long> {
        // Additional query methods can be defined here
    }
}
