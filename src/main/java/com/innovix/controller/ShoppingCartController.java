package com.innovix.controller;

import com.innovix.dto.ShoppingCartDTO;
import com.innovix.entity.Person;
import com.innovix.mapper.ShoppingCartMapper;
import com.innovix.usecase.CustomerUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for managing shopping carts.
 */
@RestController
@RequestMapping("/api/shopping-carts")
public class ShoppingCartController {

    private final CustomerUseCase customerUseCase;

    @Autowired
    public ShoppingCartController(CustomerUseCase customerUseCase) {
        this.customerUseCase = customerUseCase;
    }

    /**
     * Lists all shopping carts.
     *
     * @return A list of all shopping carts.
     */
    @GetMapping
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public List<ShoppingCartDTO> listAll() {
        return customerUseCase.listAllShoppingCarts().stream()
                .map(ShoppingCartMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Saves a new shopping cart.
     *
     * @param shoppingCartDTO The shopping cart data transfer object.
     * @return The saved shopping cart data transfer object.
     */
    @PostMapping
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ShoppingCartDTO save(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        return ShoppingCartMapper.INSTANCE.toDto(
                customerUseCase.createShoppingCart(ShoppingCartMapper.INSTANCE.toEntity(shoppingCartDTO))
        );
    }

    /**
     * Gets a shopping cart by ID.
     *
     * @param id The ID of the shopping cart.
     * @return The shopping cart data transfer object.
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ShoppingCartDTO getById(@PathVariable Long id) {
        return ShoppingCartMapper.INSTANCE.toDto(customerUseCase.getShoppingCartById(id));
    }

    /**
     * Lists shopping carts by customer ID.
     *
     * @param customerId The ID of the customer.
     * @return A list of shopping carts associated with the specified customer.
     */
    @GetMapping("/customer/{customerId}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public List<ShoppingCartDTO> listByCustomer(@PathVariable Long customerId) {
        return customerUseCase.listShoppingCartsByCustomer(new Person(customerId)).stream()
                .map(ShoppingCartMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Deletes a shopping cart by ID.
     *
     * @param id The ID of the shopping cart to delete.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public void delete(@PathVariable Long id) {
        customerUseCase.deleteShoppingCart(id);
    }
}
