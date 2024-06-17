package com.innovix.service;

import com.innovix.entity.Store;
import com.innovix.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to manage operations related to {@link Store}.
 */
@Service
public class StoreService {

    private final StoreRepository storeRepository;

    /**
     * Constructor for dependency injection.
     *
     * @param storeRepository the store repository.
     */
    @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    /**
     * Lists all stores.
     *
     * @return a list of stores.
     */
    public List<Store> listAll() {
        return storeRepository.findAll();
    }

    /**
     * Saves a new store.
     *
     * @param store the store to save.
     * @return the saved store.
     */
    public Store save(Store store) {
        return storeRepository.save(store);
    }

    /**
     * Finds a store by ID.
     *
     * @param id the ID of the store.
     * @return the found store, or {@code null} if not found.
     */
    public Store findById(Long id) {
        return storeRepository.findById(id).orElse(null);
    }

    /**
     * Finds a store by name.
     *
     * @param name the name of the store.
     * @return the found store.
     */
    public Store findByName(String name) {
        return storeRepository.findByStoreName(name);
    }

    /**
     * Finds a store by CNPJ.
     *
     * @param cnpj the CNPJ of the store.
     * @return the found store.
     */
    public Store findByCnpj(String cnpj) {
        return storeRepository.findByStoreCnpj(cnpj);
    }

    /**
     * Finds stores by city.
     *
     * @param city the city.
     * @return a list of stores in the specified city.
     */
    public List<Store> findByCity(String city) {
        return storeRepository.findByCity(city);
    }

    /**
     * Finds stores by state.
     *
     * @param state the state.
     * @return a list of stores in the specified state.
     */
    public List<Store> findByState(String state) {
        return storeRepository.findByState(state);
    }

    /**
     * Finds stores by country.
     *
     * @param country the country.
     * @return a list of stores in the specified country.
     */
    public List<Store> findByCountry(String country) {
        return storeRepository.findByCountry(country);
    }

    /**
     * Finds stores by employee ID.
     *
     * @param employeeId the employee ID.
     * @return a list of stores associated with the specified employee.
     */
    public List<Store> findByEmployeeId(Long employeeId) {
        return storeRepository.findByEmployeeId(employeeId);
    }

    /**
     * Deletes a store by ID.
     *
     * @param id the ID of the store to delete.
     */
    public void delete(Long id) {
        storeRepository.deleteById(id);
    }
}
