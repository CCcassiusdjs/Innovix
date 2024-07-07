package com.innovix.repository;

import com.innovix.entity.Address;
import com.innovix.repository.AddressRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class AddressRepositoryTest {
    @Autowired
    private AddressRepository addressRepository;

    private Long personId = 1L;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        Address address1 = new Address(null, "Main Street", 123, "Apt 1", "12345-678", "Springfield", "IL", "USA", personId);
        Address address2 = new Address(null, "Oak Avenue", 456, "Suite 2", "98765-432", "New York", "NY", "USA", personId);
        Address address3 = new Address(null, "Broadway", 789, "Floor 3", "54321-876", "Los Angeles", "CA", "USA", personId);
        addressRepository.save(address1);
        addressRepository.save(address2);
        addressRepository.save(address3);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        addressRepository.deleteAll();
    }

    @org.junit.jupiter.api.Test
    void findByCity() {
        List<Address> foundAddresses = addressRepository.findByCity("Springfield");
        assertNotNull(foundAddresses);
        assertEquals(1, foundAddresses.size());
        assertEquals("Springfield", foundAddresses.get(0).getCity());
    }

    @org.junit.jupiter.api.Test
    void findByState() {
        List<Address> foundAddresses = addressRepository.findByState("NY");
        assertNotNull(foundAddresses);
        assertEquals(1, foundAddresses.size());
        assertEquals("NY", foundAddresses.get(0).getState());
    }

    @org.junit.jupiter.api.Test
    void findByCountry() {
        List<Address> foundAddresses = addressRepository.findByCountry("USA");
        assertNotNull(foundAddresses);
        assertEquals(3, foundAddresses.size());
    }

    @org.junit.jupiter.api.Test
    void findByPersonId() {
        List<Address> foundAddresses = addressRepository.findByPersonId(personId);
        assertNotNull(foundAddresses);
        assertEquals(3, foundAddresses.size());
    }
}
