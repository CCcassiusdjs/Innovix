package com.innovix.usecase;

import com.innovix.entity.ShoppingCart;
import com.innovix.entity.Person;
import com.innovix.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Use case for managing operations related to {@link ShoppingCart}.
 */
@Component
public class ShoppingCartUseCase {

    private final ShoppingCartService shoppingCartService;

    /**
     * Constructor for dependency injection.
     *
     * @param shoppingCartService the shopping cart service.
     */
    @Autowired
    public ShoppingCartUseCase(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    /**
     * Lists all shopping carts.
     *
     * @return a list of shopping carts.
     */
    public List<ShoppingCart> listAllShoppingCarts() {
        return shoppingCartService.listAll();
    }

    /**
     * Creates a new shopping cart.
     *
     * @param shoppingCart the shopping cart to create.
     * @return the created shopping cart.
     */
    public ShoppingCart createShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartService.save(shoppingCart);
    }

    /**
     * Gets a shopping cart by ID.
     *
     * @param id the ID of the shopping cart.
     * @return the found shopping cart, or {@code null} if not found.
     */
    public ShoppingCart getShoppingCartById(Long id) {
        return shoppingCartService.findById(id);
    }

    /**
     * Lists shopping carts by customer.
     *
     * @param customer the customer.
     * @return a list of shopping carts associated with the specified customer.
     */
    public List<ShoppingCart> listShoppingCartsByCustomer(Person customer) {
        return shoppingCartService.findByCustomer(customer);
    }

    /**
     * Deletes a shopping cart by ID.
     *
     * @param id the ID of the shopping cart to delete.
     */
    public void deleteShoppingCart(Long id) {
        shoppingCartService.delete(id);
    }
}
