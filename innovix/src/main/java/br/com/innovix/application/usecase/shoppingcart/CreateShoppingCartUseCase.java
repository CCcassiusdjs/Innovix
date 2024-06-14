package br.com.innovix.application.usecase.shoppingcart;

import br.com.innovix.domain.entity.ShoppingCart;
import br.com.innovix.domain.repository.ShoppingCartRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class CreateShoppingCartUseCase {
    private final ShoppingCartRepository shoppingCartRepository;

    public CreateShoppingCartUseCase(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public ShoppingCart execute(@Valid ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }
}
