package com.innovix.service;

import com.innovix.entity.Address;
import com.innovix.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing addresses.
 * <p>
 * This class provides methods for CRUD operations on Address entities.
 * </p>
 */
@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /**
     * Lists all addresses.
     *
     * @return A list of all addresses.
     */
    public List<Address> listAll() {
        return addressRepository.findAll();
    }

    /**
     * Saves a new address or updates an existing one.
     *
     * @param address The address to save.
     * @return The saved address.
     */
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    /**
     * Finds an address by its ID.
     *
     * @param id The ID of the address.
     * @return The address with the specified ID, or null if not found.
     */
    public Address findById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    /**
     * Finds addresses by city.
     *
     * @param city The city to search for.
     * @return A list of addresses in the specified city.
     */
    public List<Address> findByCity(String city) {
        return addressRepository.findByCity(city);
    }

    /**
     * Finds addresses by state.
     *
     * @param state The state to search for.
     * @return A list of addresses in the specified state.
     */
    public List<Address> findByState(String state) {
        return addressRepository.findByState(state);
    }

    /**
     * Finds addresses by country.
     *
     * @param country The country to search for.
     * @return A list of addresses in the specified country.
     */
    public List<Address> findByCountry(String country) {
        return addressRepository.findByCountry(country);
    }

    /**
     * Finds addresses by person ID.
     *
     * @param personId The ID of the person.
     * @return A list of addresses associated with the specified person ID.
     */
    public List<Address> findByPersonId(Long personId) {
        return addressRepository.findByPersonId(personId);
    }

    /**
     * Deletes an address by its ID.
     *
     * @param id The ID of the address to delete.
     */
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }
}
