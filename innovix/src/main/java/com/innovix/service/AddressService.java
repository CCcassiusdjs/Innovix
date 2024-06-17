package com.innovix.service;

import com.innovix.entity.Address;
import com.innovix.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to manage operations related to {@link Address}.
 */
@Service
public class AddressService {

    private final AddressRepository addressRepository;

    /**
     * Constructor for dependency injection.
     *
     * @param addressRepository the address repository.
     */
    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /**
     * Lists all addresses.
     *
     * @return a list of addresses.
     */
    public List<Address> listAll() {
        return addressRepository.findAll();
    }

    /**
     * Saves a new address.
     *
     * @param address the address to save.
     * @return the saved address.
     */
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    /**
     * Finds an address by ID.
     *
     * @param id the ID of the address.
     * @return the found address, or {@code null} if not found.
     */
    public Address findById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    /**
     * Finds addresses by city.
     *
     * @param city the city.
     * @return a list of addresses in the specified city.
     */
    public List<Address> findByCity(String city) {
        return addressRepository.findByCity(city);
    }

    /**
     * Finds addresses by state.
     *
     * @param state the state.
     * @return a list of addresses in the specified state.
     */
    public List<Address> findByState(String state) {
        return addressRepository.findByState(state);
    }

    /**
     * Finds addresses by country.
     *
     * @param country the country.
     * @return a list of addresses in the specified country.
     */
    public List<Address> findByCountry(String country) {
        return addressRepository.findByCountry(country);
    }

    /**
     * Finds addresses by person ID.
     *
     * @param personId the ID of the person.
     * @return a list of addresses associated with the person.
     */
    public List<Address> findByPersonId(Long personId) {
        return addressRepository.findByPersonId(personId);
    }

    /**
     * Deletes an address by ID.
     *
     * @param id the ID of the address to delete.
     */
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }
}
