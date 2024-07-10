package com.innovix.service;

import com.innovix.entity.Store;
import com.innovix.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing stores.
 * <p>
 * This class provides methods for CRUD operations on Store entities.
 * </p>
 */
@Service
public class StoreService {

    private final StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    /**
     * Lists all stores.
     *
     * @return A list of all stores.
     */
    public List<Store> listAll() {
        return storeRepository.findAll();
    }

    /**
     * Saves a new store or updates an existing one.
     *
     * @param store The store to save.
     * @return The saved store.
     */
    public Store save(Store store) {
        return storeRepository.save(store);
    }

    /**
     * Finds a store by its ID.
     *
     * @param id The ID of the store.
     * @return The store with the specified ID, or null if not found.
     */
    public Store findById(Long id) {
        return storeRepository.findById(id).orElse(null);
    }

    /**
     * Finds a store by its name.
     *
     * @param name The name of the store.
     * @return The store with the specified name.
     */
    public Store findByName(String name) {
        return storeRepository.findByStoreName(name);
    }

    /**
     * Finds a store by its CNPJ.
     *
     * @param cnpj The CNPJ of the store.
     * @return The store with the specified CNPJ.
     */
    public Store findByCnpj(String cnpj) {
        return storeRepository.findByStoreCnpj(cnpj);
    }

    /**
     * Finds stores by city.
     *
     * @param city The city to search for.
     * @return A list of stores in the specified city.
     */
    public List<Store> findByCity(String city) {
        return storeRepository.findByAddressCity(city);
    }

    /**
     * Finds stores by state.
     *
     * @param state The state to search for.
     * @return A list of stores in the specified state.
     */
    public List<Store> findByState(String state) {
        return storeRepository.findByAddressState(state);
    }

    /**
     * Finds stores by country.
     *
     * @param country The country to search for.
     * @return A list of stores in the specified country.
     */
    public List<Store> findByCountry(String country) {
        return storeRepository.findByAddressCountry(country);
    }

    /**
     * Finds stores by employee ID.
     *
     * @param employeeId The ID of the employee.
     * @return A list of stores associated with the specified employee ID.
     */
    public List<Store> findByEmployeeId(Long employeeId) {
        return storeRepository.findByEmployeeId(employeeId);
    }

    /**
     * Deletes a store by its ID.
     *
     * @param id The ID of the store to delete.
     */
    public void delete(Long id) {
        storeRepository.deleteById(id);
    }
}
