package br.com.innovix.frameworks.repository.jpa;

import br.com.innovix.domain.entity.ShoppingCart;
import br.com.innovix.domain.repository.ShoppingCartRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaShoppingCartRepository extends JpaRepository<ShoppingCart, Long>, ShoppingCartRepository {
}
