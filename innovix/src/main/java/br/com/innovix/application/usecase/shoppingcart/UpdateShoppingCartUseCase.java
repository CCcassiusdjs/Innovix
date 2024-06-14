package br.com.innovix.application.usecase.shoppingcart;

import br.com.innovix.domain.entity.ShoppingCart;
import br.com.innovix.domain.repository.ShoppingCartRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateShoppingCartUseCase {
    private final ShoppingCartRepository shoppingCartRepository;

    public UpdateShoppingCartUseCase(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public Optional<ShoppingCart> execute(Long cartId, @Valid ShoppingCart shoppingCart) {
        return shoppingCartRepository.findById(cartId).map(existingCart -> {
            existingCart.setCustomer(shoppingCart.getCustomer());
            existingCart.setItems(shoppingCart.getItems());
            return shoppingCartRepository.save(existingCart);
        });
    }
}
