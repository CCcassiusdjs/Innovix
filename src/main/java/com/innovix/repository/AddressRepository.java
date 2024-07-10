package com.innovix.repository;

import com.innovix.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Address entity.
 * <p>
 * This interface extends JpaRepository to provide CRUD operations for Address entities.
 * </p>
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    /**
     * Finds addresses by city.
     *
     * @param city The city to search for.
     * @return A list of addresses in the specified city.
     */
    List<Address> findByCity(String city);

    /**
     * Finds addresses by state.
     *
     * @param state The state to search for.
     * @return A list of addresses in the specified state.
     */
    List<Address> findByState(String state);

    /**
     * Finds addresses by country.
     *
     * @param country The country to search for.
     * @return A list of addresses in the specified country.
     */
    List<Address> findByCountry(String country);

    /**
     * Finds addresses by person ID.
     *
     * @param personId The ID of the person to search for.
     * @return A list of addresses associated with the specified person ID.
     */
    List<Address> findByPersonId(Long personId);
}
