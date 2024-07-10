package com.innovix.repository;

import com.innovix.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Store entity.
 * <p>
 * This interface extends JpaRepository to provide CRUD operations for Store entities.
 * </p>
 */
@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    /**
     * Finds a store by its name.
     *
     * @param storeName The name of the store.
     * @return The store with the specified name.
     */
    Store findByStoreName(String storeName);

    /**
     * Finds a store by its CNPJ.
     *
     * @param storeCnpj The CNPJ of the store.
     * @return The store with the specified CNPJ.
     */
    Store findByStoreCnpj(String storeCnpj);

    /**
     * Finds stores by city.
     *
     * @param city The city to search for.
     * @return A list of stores in the specified city.
     */
    List<Store> findByAddressCity(String city);

    /**
     * Finds stores by state.
     *
     * @param state The state to search for.
     * @return A list of stores in the specified state.
     */
    List<Store> findByAddressState(String state);

    /**
     * Finds stores by country.
     *
     * @param country The country to search for.
     * @return A list of stores in the specified country.
     */
    List<Store> findByAddressCountry(String country);

    /**
     * Finds stores by employee ID.
     *
     * @param employeeId The ID of the employee.
     * @return A list of stores associated with the specified employee ID.
     */
    List<Store> findByEmployeeId(Long employeeId);
}
