package br.com.innovix.application.usecase.shoppingcart;

import br.com.innovix.domain.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteShoppingCartUseCase {
    private final ShoppingCartRepository shoppingCartRepository;

    public DeleteShoppingCartUseCase(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public void execute(Long cartId) {
        shoppingCartRepository.deleteById(cartId);
    }
}
