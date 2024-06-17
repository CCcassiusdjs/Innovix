package com.innovix.usecase;

import com.innovix.entity.Address;
import com.innovix.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Use case for managing operations related to {@link Address}.
 */
@Component
public class AddressUseCase {

    private final AddressService addressService;

    /**
     * Constructor for dependency injection.
     *
     * @param addressService the address service.
     */
    @Autowired
    public AddressUseCase(AddressService addressService) {
        this.addressService = addressService;
    }

    /**
     * Lists all addresses.
     *
     * @return a list of addresses.
     */
    public List<Address> listAllAddresses() {
        return addressService.listAll();
    }

    /**
     * Creates a new address.
     *
     * @param address the address to create.
     * @return the created address.
     */
    public Address createAddress(Address address) {
        return addressService.save(address);
    }

    /**
     * Gets an address by ID.
     *
     * @param id the ID of the address.
     * @return the found address, or {@code null} if not found.
     */
    public Address getAddressById(Long id) {
        return addressService.findById(id);
    }

    /**
     * Lists addresses by city.
     *
     * @param city the city.
     * @return a list of addresses in the specified city.
     */
    public List<Address> listAddressesByCity(String city) {
        return addressService.findByCity(city);
    }

    /**
     * Lists addresses by state.
     *
     * @param state the state.
     * @return a list of addresses in the specified state.
     */
    public List<Address> listAddressesByState(String state) {
        return addressService.findByState(state);
    }

    /**
     * Lists addresses by country.
     *
     * @param country the country.
     * @return a list of addresses in the specified country.
     */
    public List<Address> listAddressesByCountry(String country) {
        return addressService.findByCountry(country);
    }

    /**
     * Lists addresses by person ID.
     *
     * @param personId the ID of the person.
     * @return a list of addresses associated with the person.
     */
    public List<Address> listAddressesByPersonId(Long personId) {
        return addressService.findByPersonId(personId);
    }

    /**
     * Deletes an address by ID.
     *
     * @param id the ID of the address to delete.
     */
    public void deleteAddress(Long id) {
        addressService.delete(id);
    }
}
