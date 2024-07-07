package com.innovix.repository;

import com.innovix.entity.Address;
import com.innovix.entity.Person;
import com.innovix.entity.Store;
import com.innovix.repository.AddressRepository;
import com.innovix.repository.PersonRepository;
import com.innovix.repository.StoreRepository;
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
public class StoreRepositoryTest {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private Address address;

    @Autowired
    private Person person;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        address = new Address();
        address.setCity("City A");
        address.setState("State A");
        address.setCountry("Country A");

        person = new Person(1L);
        person.setFullName("John Doe");

        address = addressRepository.save(address);
        person = personRepository.save(person);
    }

    @org.junit.jupiter.api.Test
    void tearDown() {
        storeRepository.deleteAll();
        addressRepository.deleteAll();
        personRepository.deleteAll();
    }

    @org.junit.jupiter.api.Test
    void findByStoreName() {
        Store store = new Store();
        store.setStoreName("Store A");
        store.setStoreCnpj("1234567890");
        store.setAddress(address);
        store.setEmployee(person);

        storeRepository.save(store);

        Store foundStore = storeRepository.findByStoreName("Store A");
        assertNotNull(foundStore);
        assertEquals(store.getStoreName(), foundStore.getStoreName());
    }

    @org.junit.jupiter.api.Test
    void findByStoreName_NotFound() {
        Store foundStore = storeRepository.findByStoreName("Nonexistent Store");
        assertNull(foundStore);
    }

    @org.junit.jupiter.api.Test
    void findByStoreCnpj() {
        Store store = new Store();
        store.setStoreName("Store B");
        store.setStoreCnpj("0987654321");
        store.setAddress(address);
        store.setEmployee(person);

        storeRepository.save(store);

        Store foundStore = storeRepository.findByStoreCnpj("0987654321");
        assertNotNull(foundStore);
        assertEquals(store.getStoreName(), foundStore.getStoreName());
    }

    @org.junit.jupiter.api.Test
    void findByStoreCnpj_NotFound() {
        Store foundStore = storeRepository.findByStoreCnpj("0000000000");
        assertNull(foundStore);
    }

    @org.junit.jupiter.api.Test
    void findByAddressCity() {
        Store store1 = new Store();
        store1.setStoreName("Store C");
        store1.setStoreCnpj("111122223333");
        store1.setAddress(address);
        store1.setEmployee(person);

        Store store2 = new Store();
        store2.setStoreName("Store D");
        store2.setStoreCnpj("444455556666");
        store2.setAddress(address);
        store2.setEmployee(person);

        storeRepository.save(store1);
        storeRepository.save(store2);

        List<Store> storesInCityA = storeRepository.findByAddressCity("City A");
        assertEquals(2, storesInCityA.size());
        assertEquals("Store C", storesInCityA.get(0).getStoreName());
        assertEquals("Store D", storesInCityA.get(1).getStoreName());
    }

    @org.junit.jupiter.api.Test
    void findByAddressCity_NoStoresFound() {
        List<Store> storesInCityB = storeRepository.findByAddressCity("City B");
        assertEquals(0, storesInCityB.size());
    }

    @org.junit.jupiter.api.Test
    void findByAddressState() {
        Store store1 = new Store();
        store1.setStoreName("Store E");
        store1.setStoreCnpj("777788889999");
        store1.setAddress(address);
        store1.setEmployee(person);

        Store store2 = new Store();
        store2.setStoreName("Store F");
        store2.setStoreCnpj("101010101010");
        store2.setAddress(address);
        store2.setEmployee(person);

        storeRepository.save(store1);
        storeRepository.save(store2);

        List<Store> storesInStateA = storeRepository.findByAddressState("State A");
        assertEquals(2, storesInStateA.size());
        assertEquals("Store E", storesInStateA.get(0).getStoreName());
        assertEquals("Store F", storesInStateA.get(1).getStoreName());
    }

    @org.junit.jupiter.api.Test
    void findByAddressState_NoStoresFound() {
        List<Store> storesInStateB = storeRepository.findByAddressState("State B");
        assertEquals(0, storesInStateB.size());
    }

    @org.junit.jupiter.api.Test
    void findByAddressCountry() {
        Store store1 = new Store();
        store1.setStoreName("Store G");
        store1.setStoreCnpj("121212121212");
        store1.setAddress(address);
        store1.setEmployee(person);

        Store store2 = new Store();
        store2.setStoreName("Store H");
        store2.setStoreCnpj("343434343434");
        store2.setAddress(address);
        store2.setEmployee(person);

        storeRepository.save(store1);
        storeRepository.save(store2);

        List<Store> storesInCountryA = storeRepository.findByAddressCountry("Country A");
        assertEquals(2, storesInCountryA.size());
        assertEquals("Store G", storesInCountryA.get(0).getStoreName());
        assertEquals("Store H", storesInCountryA.get(1).getStoreName());
    }

    @org.junit.jupiter.api.Test
    void findByAddressCountry_NoStoresFound() {
        List<Store> storesInCountryB = storeRepository.findByAddressCountry("Country B");
        assertEquals(0, storesInCountryB.size());
    }

    @org.junit.jupiter.api.Test
    void findByEmployeeId() {
        Store store1 = new Store();
        store1.setStoreName("Store I");
        store1.setStoreCnpj("565656565656");
        store1.setAddress(address);
        store1.setEmployee(person);

        Store store2 = new Store();
        store2.setStoreName("Store J");
        store2.setStoreCnpj("787878787878");
        store2.setAddress(address);
        store2.setEmployee(person);

        storeRepository.save(store1);
        storeRepository.save(store2);

        List<Store> storesWithEmployee = storeRepository.findByEmployeeId(person.getId());
        assertEquals(2, storesWithEmployee.size());
        assertEquals("Store I", storesWithEmployee.get(0).getStoreName());
        assertEquals("Store J", storesWithEmployee.get(1).getStoreName());
    }

    @org.junit.jupiter.api.Test
    void findByEmployeeId_NoStoresFound() {
        List<Store> storesWithEmployee = storeRepository.findByEmployeeId(-1L); // Assuming -1L is an ID that doesn't exist
        assertEquals(0, storesWithEmployee.size());
    }
}
