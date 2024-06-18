package com.innovix.controller;

import com.innovix.dto.ShoppingCartDTO;
import com.innovix.entity.Person;
import com.innovix.mapper.ShoppingCartMapper;
import com.innovix.usecase.ShoppingCartUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/shopping-carts")
public class ShoppingCartController {

    private final ShoppingCartUseCase shoppingCartUseCase;

    @Autowired
    public ShoppingCartController(ShoppingCartUseCase shoppingCartUseCase) {
        this.shoppingCartUseCase = shoppingCartUseCase;
    }

    @GetMapping
    public List<ShoppingCartDTO> listAll() {
        return shoppingCartUseCase.listAllShoppingCarts().stream()
                .map(ShoppingCartMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ShoppingCartDTO save(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        return ShoppingCartMapper.INSTANCE.toDto(
                shoppingCartUseCase.createShoppingCart(ShoppingCartMapper.INSTANCE.toEntity(shoppingCartDTO))
        );
    }

    @GetMapping("/{id}")
    public ShoppingCartDTO getById(@PathVariable Long id) {
        return ShoppingCartMapper.INSTANCE.toDto(shoppingCartUseCase.getShoppingCartById(id));
    }

    @GetMapping("/customer/{customerId}")
    public List<ShoppingCartDTO> listByCustomer(@PathVariable Long customerId) {
        return shoppingCartUseCase.listShoppingCartsByCustomer(new Person(customerId)).stream()
                .map(ShoppingCartMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        shoppingCartUseCase.deleteShoppingCart(id);
    }
}
