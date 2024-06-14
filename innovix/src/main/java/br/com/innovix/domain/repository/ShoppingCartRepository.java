package br.com.innovix.domain.repository;

import br.com.innovix.domain.entity.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartRepository {
    ShoppingCart save(ShoppingCart cart);
    Optional<ShoppingCart> findById(Long cartId);
    List<ShoppingCart> findAll();
    void deleteById(Long cartId);
}
