package com.innovix.service;

import com.innovix.entity.ShoppingCart;
import com.innovix.entity.Person;
import com.innovix.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing shopping carts.
 * <p>
 * This class provides methods for CRUD operations on ShoppingCart entities.
 * </p>
 */
@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    /**
     * Lists all shopping carts.
     *
     * @return A list of all shopping carts.
     */
    public List<ShoppingCart> listAll() {
        return shoppingCartRepository.findAll();
    }

    /**
     * Saves a new shopping cart or updates an existing one.
     *
     * @param shoppingCart The shopping cart to save.
     * @return The saved shopping cart.
     */
    public ShoppingCart save(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    /**
     * Finds a shopping cart by its ID.
     *
     * @param id The ID of the shopping cart.
     * @return The shopping cart with the specified ID, or null if not found.
     */
    public ShoppingCart findById(Long id) {
        return shoppingCartRepository.findById(id).orElse(null);
    }

    /**
     * Finds shopping carts by customer.
     *
     * @param customer The customer associated with the shopping cart.
     * @return A list of shopping carts for the specified customer.
     */
    public List<ShoppingCart> findByCustomer(Person customer) {
        return shoppingCartRepository.findByCustomer(customer);
    }

    /**
     * Deletes a shopping cart by its ID.
     *
     * @param id The ID of the shopping cart to delete.
     */
    public void delete(Long id) {
        shoppingCartRepository.deleteById(id);
    }
}
