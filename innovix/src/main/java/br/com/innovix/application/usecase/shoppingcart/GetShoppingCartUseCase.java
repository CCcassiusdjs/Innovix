package br.com.innovix.application.usecase.shoppingcart;

import br.com.innovix.domain.entity.ShoppingCart;
import br.com.innovix.domain.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetShoppingCartUseCase {
    private final ShoppingCartRepository shoppingCartRepository;

    public GetShoppingCartUseCase(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public Optional<ShoppingCart> execute(Long cartId) {
        return shoppingCartRepository.findById(cartId);
    }

    public List<ShoppingCart> execute() {
        return shoppingCartRepository.findAll();
    }
}
