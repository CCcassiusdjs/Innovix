package com.innovix.service;

import com.innovix.entity.ShoppingCart;
import com.innovix.entity.Person;
import com.innovix.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to manage operations related to {@link ShoppingCart}.
 */
@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    /**
     * Constructor for dependency injection.
     *
     * @param shoppingCartRepository the shopping cart repository.
     */
    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    /**
     * Lists all shopping carts.
     *
     * @return a list of shopping carts.
     */
    public List<ShoppingCart> listAll() {
        return shoppingCartRepository.findAll();
    }

    /**
     * Saves a new shopping cart.
     *
     * @param shoppingCart the shopping cart to save.
     * @return the saved shopping cart.
     */
    public ShoppingCart save(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    /**
     * Finds a shopping cart by ID.
     *
     * @param id the ID of the shopping cart.
     * @return the found shopping cart, or {@code null} if not found.
     */
    public ShoppingCart findById(Long id) {
        return shoppingCartRepository.findById(id).orElse(null);
    }

    /**
     * Finds shopping carts by customer.
     *
     * @param customer the customer.
     * @return a list of shopping carts associated with the specified customer.
     */
    public List<ShoppingCart> findByCustomer(Person customer) {
        return shoppingCartRepository.findByCustomer(customer);
    }

    /**
     * Deletes a shopping cart by ID.
     *
     * @param id the ID of the shopping cart to delete.
     */
    public void delete(Long id) {
        shoppingCartRepository.deleteById(id);
    }
}
