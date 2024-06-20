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

@RestController
@RequestMapping("/api/shopping-carts")
public class ShoppingCartController {

    private final CustomerUseCase customerUseCase;

    @Autowired
    public ShoppingCartController(CustomerUseCase customerUseCase) {
        this.customerUseCase = customerUseCase;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public List<ShoppingCartDTO> listAll() {
        return customerUseCase.listAllShoppingCarts().stream()
                .map(ShoppingCartMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ShoppingCartDTO save(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        return ShoppingCartMapper.INSTANCE.toDto(
                customerUseCase.createShoppingCart(ShoppingCartMapper.INSTANCE.toEntity(shoppingCartDTO))
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ShoppingCartDTO getById(@PathVariable Long id) {
        return ShoppingCartMapper.INSTANCE.toDto(customerUseCase.getShoppingCartById(id));
    }

    @GetMapping("/customer/{customerId}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public List<ShoppingCartDTO> listByCustomer(@PathVariable Long customerId) {
        return customerUseCase.listShoppingCartsByCustomer(new Person(customerId)).stream()
                .map(ShoppingCartMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public void delete(@PathVariable Long id) {
        customerUseCase.deleteShoppingCart(id);
    }
}
