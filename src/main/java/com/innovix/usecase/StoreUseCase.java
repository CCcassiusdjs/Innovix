package com.innovix.usecase;

import com.innovix.entity.Store;
import com.innovix.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Use case for managing operations related to {@link Store}.
 */
@Component
public class StoreUseCase {

    private final StoreService storeService;

    /**
     * Constructor for dependency injection.
     *
     * @param storeService the store service.
     */
    @Autowired
    public StoreUseCase(StoreService storeService) {
        this.storeService = storeService;
    }

    /**
     * Lists all stores.
     *
     * @return a list of stores.
     */
    public List<Store> listAllStores() {
        return storeService.listAll();
    }

    /**
     * Creates a new store.
     *
     * @param store the store to create.
     * @return the created store.
     */
    public Store createStore(Store store) {
        return storeService.save(store);
    }

    /**
     * Gets a store by ID.
     *
     * @param id the ID of the store.
     * @return the found store, or {@code null} if not found.
     */
    public Store getStoreById(Long id) {
        return storeService.findById(id);
    }

    /**
     * Gets a store by name.
     *
     * @param name the name of the store.
     * @return the found store.
     */
    public Store getStoreByName(String name) {
        return storeService.findByName(name);
    }

    /**
     * Gets a store by CNPJ.
     *
     * @param cnpj the CNPJ of the store.
     * @return the found store.
     */
    public Store getStoreByCnpj(String cnpj) {
        return storeService.findByCnpj(cnpj);
    }

    /**
     * Lists stores by city.
     *
     * @param city the city.
     * @return a list of stores in the specified city.
     */
    public List<Store> listStoresByCity(String city) {
        return storeService.findByCity(city);
    }

    /**
     * Lists stores by state.
     *
     * @param state the state.
     * @return a list of stores in the specified state.
     */
    public List<Store> listStoresByState(String state) {
        return storeService.findByState(state);
    }

    /**
     * Lists stores by country.
     *
     * @param country the country.
     * @return a list of stores in the specified country.
     */
    public List<Store> listStoresByCountry(String country) {
        return storeService.findByCountry(country);
    }

    /**
     * Lists stores by employee ID.
     *
     * @param employeeId the employee ID.
     * @return a list of stores associated with the specified employee.
     */
    public List<Store> listStoresByEmployeeId(Long employeeId) {
        return storeService.findByEmployeeId(employeeId);
    }

    /**
     * Deletes a store by ID.
     *
     * @param id the ID of the store to delete.
     */
    public void deleteStore(Long id) {
        storeService.delete(id);
    }
}
