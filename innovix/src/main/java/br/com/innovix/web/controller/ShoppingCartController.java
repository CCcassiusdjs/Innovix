package br.com.innovix.web.controller;

import br.com.innovix.application.usecase.shoppingcart.CreateShoppingCartUseCase;
import br.com.innovix.application.usecase.shoppingcart.DeleteShoppingCartUseCase;
import br.com.innovix.application.usecase.shoppingcart.GetShoppingCartUseCase;
import br.com.innovix.application.usecase.shoppingcart.UpdateShoppingCartUseCase;
import br.com.innovix.domain.entity.ShoppingCart;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shoppingcarts")
public class ShoppingCartController {
    @Autowired
    private CreateShoppingCartUseCase createShoppingCartUseCase;

    @Autowired
    private GetShoppingCartUseCase getShoppingCartUseCase;

    @Autowired
    private UpdateShoppingCartUseCase updateShoppingCartUseCase;

    @Autowired
    private DeleteShoppingCartUseCase deleteShoppingCartUseCase;

    @PostMapping
    public ShoppingCart createShoppingCart(@RequestBody @Valid ShoppingCart shoppingCart) {
        return createShoppingCartUseCase.execute(shoppingCart);
    }

    @GetMapping("/{id}")
    public Optional<ShoppingCart> getShoppingCart(@PathVariable Long id) {
        return getShoppingCartUseCase.execute(id);
    }

    @GetMapping
    public List<ShoppingCart> getAllShoppingCarts() {
        return getShoppingCartUseCase.execute();
    }

    @PutMapping("/{id}")
    public Optional<ShoppingCart> updateShoppingCart(@PathVariable Long id, @RequestBody @Valid ShoppingCart shoppingCart) {
        return updateShoppingCartUseCase.execute(id, shoppingCart);
    }

    @DeleteMapping("/{id}")
    public void deleteShoppingCart(@PathVariable Long id) {
        deleteShoppingCartUseCase.execute(id);
    }
}
