package com.innovix.repository;

import com.innovix.entity.ShoppingCart;
import com.innovix.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for ShoppingCart entity.
 * <p>
 * This interface extends JpaRepository to provide CRUD operations for ShoppingCart entities.
 * </p>
 */
@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    /**
     * Finds shopping carts by customer.
     *
     * @param customer The customer associated with the shopping cart.
     * @return A list of shopping carts for the specified customer.
     */
    List<ShoppingCart> findByCustomer(Person customer);
}
